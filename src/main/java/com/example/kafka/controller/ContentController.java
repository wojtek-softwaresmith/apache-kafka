package com.example.kafka.controller;

import com.example.kafka.domain.Payload;
import com.example.kafka.config.KafkaProducer;
import com.example.kafka.security.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContentController {

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    private final KafkaProducer kafkaProducer;

    public ContentController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/content")
    public ResponseEntity<Void> sendMessage(@RequestBody String content, HttpServletRequest request) {

        String clientIpAddress = SecurityUtils.getClientIpAddress(request);
        String userAgentHeader = request.getHeader("user-agent");

        kafkaProducer.sendPayload(topicName, new Payload(content, clientIpAddress, userAgentHeader));

        return ResponseEntity.noContent().build();
    }
}
