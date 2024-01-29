package com.example.kafka.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "my-topic", groupId = "group-id")
    public void listen(String message) {
        System.out.println(STR."Received message: \{message}");
    }
}
