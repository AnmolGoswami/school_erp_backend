package com.schoolErp;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateInterceptorConfig {

    @Bean
    public TenantInterceptor tenantInterceptor(TenantContextHolder tenantContextHolder) {
        return new TenantInterceptor(tenantContextHolder);
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(TenantInterceptor tenantInterceptor) {
        return hibernateProperties -> {
            hibernateProperties.put("hibernate.session_factory.statement_inspector", tenantInterceptor);
            // If you want to use onSave/onFlushDirty too:
            // hibernateProperties.put("hibernate.session_factory.interceptor", tenantInterceptor);
        };
    }
}
