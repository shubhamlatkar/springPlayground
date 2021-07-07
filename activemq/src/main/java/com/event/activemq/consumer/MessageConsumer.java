package com.event.activemq.consumer;

import com.event.activemq.model.SystemMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {


    @JmsListener(destination = "test-queue")
    public void messageListener(SystemMessage systemMessage) {
        log.info("Message received! {}", systemMessage);
    }
}
