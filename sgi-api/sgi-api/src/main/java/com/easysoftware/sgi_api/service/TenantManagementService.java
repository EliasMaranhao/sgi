package com.easysoftware.sgi_api.service;

import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;

@Service
public class TenantManagementService {
    
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public TenantManagementService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @Transactional
    public void createNewTenantSchema(String tenantId) {
        // 1. Sanitizar o nome do tenant para evitar SQL Injection no nome do schema
        String schemaName = tenantId.replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();

        // 2. Criar o Schema isolado no banco de dados
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);

        // 3. Rodar as migrações do Flyway especificamente dentro deste novo schema
        runFlywayMigrationForSchema(schemaName);
    }

    private void runFlywayMigrationForSchema(String schemaName) {
        // Criamos uma instância dedicada do Flyway apontando para o novo schema
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(schemaName)
                // Localização dos seus scripts SQL (ex: src/main/resources/db/migration/tenant)
                .locations("classpath:db/migration/tenant") 
                .baselineOnMigrate(true)
                .load();

        // Executa a criação de todas as tabelas do SGI neste schema
        flyway.migrate();
    }
}
