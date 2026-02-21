package com.schoolErp;

import jakarta.servlet.*;
import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantClearingFilter implements Filter {
    private final TenantContextHolder tenantContextHolder;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } finally {
            tenantContextHolder.clear();
        }
    }
}