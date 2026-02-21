package com.schoolErp;

import org.springframework.stereotype.Component;

@Component
public class TenantContextHolder {

    private static final ThreadLocal<Long> currentTenant = new ThreadLocal<>();

    public void setCurrentTenantId(Long tenantId) {
        currentTenant.set(tenantId);
    }

    public Long getCurrentTenantId() {
        return currentTenant.get();
    }

    public void clear() {
        currentTenant.remove();
    }
}
