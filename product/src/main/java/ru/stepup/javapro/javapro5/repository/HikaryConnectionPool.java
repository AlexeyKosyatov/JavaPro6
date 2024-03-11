package ru.stepup.javapro.javapro5.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class HikaryConnectionPool {
    @Value("${database.driver-class-name}")
    private String driverClassName;

    @Value("${database.jdbc-url}")
    private String jdbcUrl;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.maximum-pool-size}")
    private int maximumPoolSize;

    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);
        return new HikariDataSource(config);
    }

}
