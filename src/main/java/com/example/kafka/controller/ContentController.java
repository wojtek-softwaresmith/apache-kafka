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

    private final KafkaProducer messageProducer;

    public ContentController(KafkaProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/content")
    public ResponseEntity<String> sendMessage(@RequestBody String content, HttpServletRequest request) {

        String clientIpAddress = SecurityUtils.getClientIpAddress(request);
        String userAgentHeader = request.getHeader("user-agent");

        messageProducer.sendPayload(topicName, new Payload(content, clientIpAddress, userAgentHeader));
        String response = "Message sent to Kafka Topic: ${$kafka.topic.name} successfully.";
        return ResponseEntity.ok(response);
    }
}
