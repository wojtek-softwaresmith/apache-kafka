package com.example.kafka.config;

import com.example.kafka.domain.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePayload(Payload payload) {
        System.out.println(STR."Payload received: \{payload}");
    }
}
