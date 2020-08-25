package com.springkafka.kafkaproducer.controller;

import com.springkafka.kafkaproducer.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class UserController {

    private final MessageChannel output;

    public UserController(MessageChannel output) {
        this.output = output;
    }


    @PostMapping("/")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        output.send(MessageBuilder.withPayload(user).build());
        return ResponseEntity.ok(user);
    }
}
