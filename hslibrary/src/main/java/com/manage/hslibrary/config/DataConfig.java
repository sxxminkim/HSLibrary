package com.manage.hslibrary.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.*;
@Configuration
@ComponentScan("com.manage.hslibrary")
public class DataConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://hslibrary.cxnldqrql9ej.ap-southeast-2.rds.amazonaws.com:3306/hslibrary");
        dataSource.setUsername("admin");

        dataSource.setPassword("hslibrary");
        return dataSource;
    }
}
