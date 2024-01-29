package com.example.kafka.controller;

import com.example.kafka.config.MessageProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KafkaController {

    private static final String TOPIC = "my-topic";

    private final MessageProducer messageProducer;

    public KafkaController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        messageProducer.sendMessage(TOPIC, message);
        return STR."Message sent to Kafka Topic: \{TOPIC} successfully.";
    }
}
