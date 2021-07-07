package com.event.activemq.component;

import com.event.activemq.model.SystemMessage;
import com.event.activemq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PromoteActProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    private static int a = 1;

    @Scheduled(fixedDelay = 2000)
    public void send() {
        a++;
        this.jmsMessagingTemplate.convertAndSend("test-queue", new SystemMessage(a + " ", userRepository.findAll().toString()));
    }
}