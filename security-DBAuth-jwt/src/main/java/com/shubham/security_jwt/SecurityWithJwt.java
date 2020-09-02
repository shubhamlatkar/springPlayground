package com.shubham.security_jwt;

import com.shubham.security_jwt.bean.UserBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SecurityWithJwt {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWithJwt.class, args);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserBean getCurrentUser() {
        return new UserBean();
    }

}
