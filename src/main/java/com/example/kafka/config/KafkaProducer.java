package com.example.kafka.config;

import com.example.kafka.domain.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Payload> payloadKafkaTemplate;

    public void sendPayload(String topic, Payload payload) {
        payloadKafkaTemplate.send(topic, payload);
    }
}
