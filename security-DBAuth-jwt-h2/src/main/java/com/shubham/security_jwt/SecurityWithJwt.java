package com.shubham.security_jwt;

import com.shubham.security_jwt.security.jwt.JwtTokenUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring-boot Auth with h2 and JWT", description = "Spring boot auth"))
public class SecurityWithJwt {
    public static void main(String[] args) {
        SpringApplication.run(SecurityWithJwt.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    CommandLineRunner getSecretKey() {
        return secret -> {
            ResponseEntity<String> response
                    = restTemplate.getForEntity("https://keygen.io/api.php?name=sha512", String.class);
            jwtTokenUtil.setSecret(response.getBody());
        };
    }
}
