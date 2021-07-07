package com.event.activemq.publisher.controller;

import com.event.activemq.model.SystemMessage;
import com.event.activemq.model.User;
import com.event.activemq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/publishMessage")
    public ResponseEntity<Object> publishMessage(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("test-queue", systemMessage);
            userRepository.save(new User(systemMessage.getMessage()));
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
