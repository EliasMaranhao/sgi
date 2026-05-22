package com.easysoftware.sgi_api.config.tenant;

import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.easysoftware.sgi_api.repository.MatrizRepository;

@Configuration
public class TenantFlywayMigrationInitializer {
    
    private final DataSource dataSource;
    private final MatrizRepository matrizRepository;

    public TenantFlywayMigrationInitializer(DataSource dataSource, MatrizRepository matrizRepository) {
        this.dataSource = dataSource;
        this.matrizRepository = matrizRepository;
    }

    /**
     * Esse evento garante que o Spring Boot já inicializou completamente,
     * abriu e fechou o contexto inicial, liberando o pool para o Flyway rodar em paz.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void migrateTenants() {
        System.out.println("Iniciando migração dos schemas dos inquilinos (Multi-tenancy)...");
        
        List<String> tenants = obterListaDeTenants();

        if (tenants.isEmpty()) {
            System.out.println("Nenhum tenant encontrado na tabela Matriz para migração.");
            return;
        }

        for (String tenant : tenants) {

            if ("sgi_public".equalsIgnoreCase(tenant)) { // Pula o banco central
                continue;
            }

            System.out.println("Roteando Flyway para o schema: " + tenant);
            try {
                Flyway flyway = Flyway.configure()
                        .dataSource(dataSource)
                        .locations("classpath:db/migration/tenant")
                        .schemas(tenant)
                        .baselineOnMigrate(true)
                        .load();

                flyway.migrate();
                System.out.println("-> Sucesso: Schema \"" + tenant + "\" atualizado.");
                
            } catch (Exception e) {
                System.err.println("-> ERRO no schema \"" + tenant + "\": " + e.getMessage());
            }
        }
        System.out.println("Processo de migração de tenants finalizado.");
    }

    private List<String> obterListaDeTenants() {
        try {
            return matrizRepository.findAll()
                    .stream()
                    .map(matriz -> matriz.getSchemaName().toLowerCase().trim())
                    .filter(name -> !name.isEmpty())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Erro crítico ao ler tabela de matrizes: " + e.getMessage());
            return List.of();
        }
    }
}
