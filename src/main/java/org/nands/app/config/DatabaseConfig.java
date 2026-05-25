package org.nands.app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                System.getenv("DB_URL")
        );

        config.setUsername(
                System.getenv("DB_USER")
        );

        config.setPassword(
                System.getenv("DB_PASSWORD")
        );

        config.setDriverClassName(
                "com.mysql.cj.jdbc.Driver"
        );

        // Pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);

        // Timeout
        config.setConnectionTimeout(10000);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(1800000);

        // Performance
        config.addDataSourceProperty(
                "cachePrepStmts",
                "true"
        );

        config.addDataSourceProperty(
                "prepStmtCacheSize",
                "250"
        );

        config.addDataSourceProperty(
                "prepStmtCacheSqlLimit",
                "2048"
        );

        dataSource =
                new HikariDataSource(config);
    }

    private DatabaseConfig() {
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}