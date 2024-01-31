package com.example.kafka.config;

import com.example.kafka.domain.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @org.springframework.kafka.annotation.KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePayload(Payload payload) {
        log.info(STR."Payload received: \{payload}");
    }
}
