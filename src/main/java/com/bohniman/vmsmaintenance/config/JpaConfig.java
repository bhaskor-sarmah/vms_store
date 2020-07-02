package com.bohniman.vmsmaintenance.config;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JpaConfig
 */
@Configuration
public class JpaConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(
                "jdbc:mysql://localhost:3306/vms?characterEncoding=UTF-8&serverTimezone=IST&zeroDateTimeBehavior=convertToNull");
        dataSourceBuilder.username("vms");
        dataSourceBuilder.password("vms");
        return dataSourceBuilder.build();
    }
}