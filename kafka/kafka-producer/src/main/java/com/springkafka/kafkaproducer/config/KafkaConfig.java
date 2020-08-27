package com.springkafka.kafkaproducer.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaConfig {
    @Output
    public MessageChannel kafkaExample();

}
