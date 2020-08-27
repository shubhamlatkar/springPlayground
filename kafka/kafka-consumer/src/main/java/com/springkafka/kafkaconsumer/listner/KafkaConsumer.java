package com.springkafka.kafkaconsumer.listner;

import com.springkafka.kafkaconsumer.model.User;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {


    @StreamListener(target = "kafkaExample")
    public void consumeUser(User user) {
        System.out.println(user);
    }

}
