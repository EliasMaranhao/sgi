package com.easysoftware.sgi_api.config.tenant;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor implements HandlerInterceptor{
    
    private static final String TENANT_HEADER = "X-Tenant-ID";
    private static final String DEFAULT_SCHEMA = "public";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // IGNORA requisições OPTIONS (CORS Preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true; 
        }
        
        String tenantId = request.getHeader(TENANT_HEADER);
        
        if (tenantId != null && !tenantId.trim().isEmpty()) {
            // Sanitiza: remove caracteres que não sejam letras, números ou underscores
            String sanitizedTenant = tenantId.trim().toLowerCase().replaceAll("[^a-z0-9_]", "");
            TenantContext.setCurrentTenant(sanitizedTenant.isEmpty() ? DEFAULT_SCHEMA : sanitizedTenant);
        } else {
            TenantContext.setCurrentTenant(DEFAULT_SCHEMA);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TenantContext.clear(); // Limpeza impecável do ThreadLocal
    }
}
