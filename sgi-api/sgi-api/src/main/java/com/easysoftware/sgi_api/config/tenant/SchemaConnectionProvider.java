package com.easysoftware.sgi_api.config.tenant;

import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

@Component
public class SchemaConnectionProvider implements MultiTenantConnectionProvider<String>{

    private final DataSource dataSource;

    public SchemaConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {
        return MultiTenantConnectionProvider.class.isAssignableFrom(unwrapType) 
                || DataSource.class.isAssignableFrom(unwrapType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> unwrapType) {
        if (isUnwrappableAs(unwrapType)) {
            return (T) this;
        }
        throw new IllegalArgumentException("Não é possível desempacotar para " + unwrapType.getName());
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = getAnyConnection();
        try {
            // Em dialetos modernos do JDBC e Spring Boot 4, você pode usar o próprio método setSchema
            // se o driver do seu banco (ex: PostgreSQL) der suporte total nativo.
            connection.setSchema(tenantIdentifier);
        } catch (SQLException e) {
            connection.close();
            throw new SQLException("Erro ao alternar para o schema: " + tenantIdentifier, e);
        }
        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.setSchema("public");
        } catch (SQLException e) {
            // Ignora ou loga o erro ao resetar
        }

        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }
    
}
