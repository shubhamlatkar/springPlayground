package com.spring_mvn.config;

import com.spring_mvn.dao.EmployeeDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public EmployeeDao employeeDao() {
        return new EmployeeDao();
    }
}
