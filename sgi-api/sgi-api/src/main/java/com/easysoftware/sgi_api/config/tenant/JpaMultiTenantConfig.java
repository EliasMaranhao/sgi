package com.easysoftware.sgi_api.config.tenant;

import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaMultiTenantConfig implements HibernatePropertiesCustomizer{

    private final TenantIdentifierResolver tenantResolver;
    private final SchemaConnectionProvider connectionProvider;

    // O Spring injeta automaticamente os seus dois componentes criados com @Component
    public JpaMultiTenantConfig(TenantIdentifierResolver tenantResolver, SchemaConnectionProvider connectionProvider) {
        this.tenantResolver = tenantResolver;
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        // Injeta programaticamente as instâncias dos Beans direto no Hibernate antes de criar o EntityManager
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
    }
    
}
