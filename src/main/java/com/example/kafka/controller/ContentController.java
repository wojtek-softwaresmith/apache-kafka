package com.example.kafka.controller;

import com.example.kafka.config.ApplicationProperties;
import com.example.kafka.domain.Payload;
import com.example.kafka.config.KafkaProducer;
import com.example.kafka.security.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContentController {

    private final KafkaProducer kafkaProducer;
    private final ApplicationProperties applicationProperties;

    public ContentController(KafkaProducer kafkaProducer,
                             ApplicationProperties applicationProperties) {
        this.kafkaProducer = kafkaProducer;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/content")
    public ResponseEntity<Void> sendMessage(@RequestBody String content, HttpServletRequest request) {

        String clientIpAddress = SecurityUtils.getClientIpAddress(request);
        String userAgentHeader = request.getHeader("user-agent");

        kafkaProducer.sendPayload(applicationProperties.getKafka().getTopicName(), new Payload(content, clientIpAddress, userAgentHeader));

        return ResponseEntity.noContent().build();
    }
}
