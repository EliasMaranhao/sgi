package com.easysoftware.sgi_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.easysoftware.sgi_api.config.tenant.TenantInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

private final TenantInterceptor tenantInterceptor;

    public WebConfig(TenantInterceptor tenantInterceptor) {
        this.tenantInterceptor = tenantInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor)
                // Aplica o interceptor para todas as rotas da sua API de negócio
                .addPathPatterns("/api/**", "/**") 
                
                // Exclui rotas públicas e ferramentas de documentação para ganho de performance
                .excludePathPatterns(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/actuator/**",
                    "/favicon.ico"
                );
    }
}
