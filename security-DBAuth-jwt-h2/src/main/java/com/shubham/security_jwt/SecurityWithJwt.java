package com.shubham.security_jwt;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring-boot Auth with h2 and JWT", description = "Spring boot auth"))
public class SecurityWithJwt {
    public static void main(String[] args) {
        SpringApplication.run(SecurityWithJwt.class, args);
    }
}
