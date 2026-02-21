package com.schoolErp;

import org.hibernate.Interceptor;                    // ← modern base interface
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.hibernate.type.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Hibernate Interceptor that automatically injects tenant_id condition
 * into every SQL query WHERE clause (multi-tenancy row-level security).
 *
 * Uses modern Hibernate 6+ Interceptor interface (EmptyInterceptor is deprecated).
 */
@Component
public class TenantInterceptor implements Interceptor, StatementInspector {

    private static final Logger log = LoggerFactory.getLogger(TenantInterceptor.class);

    private final TenantContextHolder tenantContextHolder;

    public TenantInterceptor(TenantContextHolder tenantContextHolder) {
        this.tenantContextHolder = tenantContextHolder;
    }

    /**
     * Inject tenant_id into SELECT statements (main use-case for filtering)
     */
    @Override
    public String inspect(String sql) {
        Long tenantId = tenantContextHolder.getCurrentTenantId();

        if (tenantId == null) {
            return sql; // no tenant context → no filtering (login, migrations, etc.)
        }

        String lowerSql = sql.toLowerCase();

        // Skip DDL, INSERT/UPDATE/DELETE without WHERE, or already filtered
        if (isDdlStatement(lowerSql) ||
                lowerSql.startsWith("insert") ||
                lowerSql.startsWith("update") ||
                (lowerSql.startsWith("delete") && !lowerSql.contains("where")) ||
                lowerSql.contains("tenant_id")) {
            return sql;
        }

        // SELECT with WHERE → append AND tenant_id = ?
        if (lowerSql.contains("where")) {
            return sql.replaceFirst("(?i)where", "WHERE tenant_id = " + tenantId + " AND ");
        }

        // SELECT without WHERE → insert WHERE tenant_id = ? before ORDER BY/LIMIT/OFFSET
        if (lowerSql.contains("from")) {
            int insertPos = sql.length();
            int orderByIndex = lowerSql.indexOf("order by");
            int limitIndex   = lowerSql.indexOf("limit");
            int offsetIndex  = lowerSql.indexOf("offset");

            if (orderByIndex > 0) insertPos = Math.min(insertPos, orderByIndex);
            if (limitIndex   > 0) insertPos = Math.min(insertPos, limitIndex);
            if (offsetIndex  > 0) insertPos = Math.min(insertPos, offsetIndex);

            String prefix = sql.substring(0, insertPos);
            String suffix = sql.substring(insertPos);

            return prefix + " WHERE tenant_id = " + tenantId + suffix;
        }

        log.debug("Injected tenant_id filter: {}", sql);
        return sql;
    }

    /**
     * Inject tenant_id during INSERT / UPDATE if missing
     */
    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState,
                                Object[] previousState, String[] propertyNames, Type[] types) {
        return injectTenantId(entity, currentState, propertyNames);
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state,
                          String[] propertyNames, Type[] types) {
        return injectTenantId(entity, state, propertyNames);
    }

    private boolean injectTenantId(Object entity, Object[] state, String[] propertyNames) {
        Long tenantId = tenantContextHolder.getCurrentTenantId();
        if (tenantId == null) {
            return false;
        }

        for (int i = 0; i < propertyNames.length; i++) {
            String prop = propertyNames[i];
            if ("tenant".equals(prop) || "tenantId".equals(prop) || "tenant_id".equals(prop)) {
                if (state[i] == null) {
                    state[i] = tenantId;
                    log.debug("Injected tenant_id = {} into entity: {}", tenantId, entity.getClass().getSimpleName());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDdlStatement(String lowerSql) {
        return lowerSql.startsWith("create") ||
                lowerSql.startsWith("alter") ||
                lowerSql.startsWith("drop") ||
                lowerSql.startsWith("truncate") ||
                lowerSql.contains("information_schema") ||
                lowerSql.contains("pg_catalog");
    }

    // Optional cleanup method (call from filter if needed)
    public void clearTenantContext() {
        tenantContextHolder.clear();
    }
}