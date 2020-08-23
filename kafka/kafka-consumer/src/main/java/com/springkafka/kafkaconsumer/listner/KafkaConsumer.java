package com.springkafka.kafkaconsumer.listner;

import com.springkafka.kafkaconsumer.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Example", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
    public void userConsumer(User user) {
        System.out.println(user);
    }

}
