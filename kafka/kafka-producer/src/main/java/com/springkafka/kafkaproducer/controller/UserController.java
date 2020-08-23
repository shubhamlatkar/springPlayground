package com.springkafka.kafkaproducer.controller;

import com.springkafka.kafkaproducer.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class UserController {


    private KafkaTemplate<String, User> kafkaTemplate;
    private static final String TOPIC = "Kafka_Example";

    public UserController(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> defaultGet(@PathVariable String username) {
        User user = new User(1l, username);
        kafkaTemplate.send(TOPIC, user);
        return ResponseEntity.ok(user);
    }
}
