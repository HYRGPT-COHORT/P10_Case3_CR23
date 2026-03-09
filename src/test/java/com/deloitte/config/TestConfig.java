package com.deloitte.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Test configuration class for Spring context
 * Excludes web MVC configuration to avoid servlet context issues
 */
@Configuration
@ComponentScan(basePackages = "com.deloitte", excludeFilters = {
    @ComponentScan.Filter(Configuration.class)
})
public class TestConfig {

    @Bean
    DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/flight_reservation_system_db");
        ds.setUsername("root");
        ds.setPassword("Newuser123");
        return ds;
    }
    
    @Bean
    JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }
}