package com.springcloud.api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
        @GetMapping("/orderFallback")
    public Mono<String> orderFallback() {
        return Mono.just("Order service down");
    }

    @GetMapping("/userFallback")
    public Mono<String> userFallback() {
        return Mono.just("User service down");
    }

}
