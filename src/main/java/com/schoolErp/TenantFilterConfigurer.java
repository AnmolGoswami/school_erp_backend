package com.schoolErp;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class TenantFilterConfigurer implements HibernatePropertiesCustomizer {

    private final TenantContextHolder tenantContext;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", new TenantInterceptor(tenantContext));
    }
}
