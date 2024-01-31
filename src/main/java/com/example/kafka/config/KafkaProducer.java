package com.example.kafka.config;

import com.example.kafka.domain.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, Payload> payloadKafkaTemplate;

    public void sendPayload(String topic, Payload payload) {
        CompletableFuture<SendResult<String, Payload>> future = payloadKafkaTemplate.send(topic, payload);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info(STR."Sent message=[\{payload}] with offset=[\{result.getRecordMetadata().offset()}]");
            } else {
                log.error(STR."Unable to send message=[\{payload}] due to : \{ex.getMessage()}");
            }
        });
    }
}
