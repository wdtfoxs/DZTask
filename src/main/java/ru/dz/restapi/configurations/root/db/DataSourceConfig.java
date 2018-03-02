package ru.dz.restapi.configurations.root.db;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.dz.restapi.dao")
public class DataSourceConfig {

    private static final String DB_PASSWORD = "db.password";
    private static final String DB_NAME = "db.databaseName";
    private static final String DB_USER = "db.username";
    private static final String DB_PORT = "db.port";
    private static final String DB_HOST = "db.host";
    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariDataSource proxyDataSource = new HikariDataSource();
        proxyDataSource.setDataSource(this.getRealDataSource());
        proxyDataSource.setUsername(environment.getRequiredProperty(DB_USER));
        proxyDataSource.setPassword(environment.getRequiredProperty(DB_PASSWORD));
        proxyDataSource.setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 2 + 2);
        return proxyDataSource;
    }

    private DataSource getRealDataSource() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setDatabaseName(environment.getRequiredProperty(DB_NAME));
        pgSimpleDataSource.setPortNumber(environment.getRequiredProperty(DB_PORT, Integer.class));
        pgSimpleDataSource.setServerName(environment.getRequiredProperty(DB_HOST));
        return pgSimpleDataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:db/db-changelog.xml");
        return liquibase;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
