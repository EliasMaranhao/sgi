package com.easysoftware.sgi_api.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver{

    @Override
    public Object resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getTenantId();
        return (tenantId != null) ? tenantId : "DEFAULT";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
    
}
