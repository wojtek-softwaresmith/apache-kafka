package com.example.kafka.config;

import com.example.kafka.domain.Payload;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    public final ApplicationProperties applicationProperties;

    public KafkaConsumerConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public ConsumerFactory<String, Payload> payloadConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, applicationProperties.getKafka().getBootstrapAddress());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, applicationProperties.getKafka().getGroupId());
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(Payload.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payload> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Payload> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(payloadConsumerFactory());
        return factory;
    }
}
