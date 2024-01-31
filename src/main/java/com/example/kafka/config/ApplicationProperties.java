package com.example.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public final KafkaProperties kafka = new KafkaProperties();

    public KafkaProperties getKafka() {
        return kafka;
    }

    public static class KafkaProperties {

        private String bootstrapAddress;
        private String groupId;
        private String topicName;

        public String getBootstrapAddress() {
            return bootstrapAddress;
        }

        public void setBootstrapAddress(String bootstrapAddress) {
            this.bootstrapAddress = bootstrapAddress;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
