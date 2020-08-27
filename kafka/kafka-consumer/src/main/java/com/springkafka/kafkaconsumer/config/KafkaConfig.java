package com.springkafka.kafkaconsumer.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaConfig {
    @Input
    public SubscribableChannel kafkaExample();
}
