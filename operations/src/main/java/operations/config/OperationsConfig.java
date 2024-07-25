package operations.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.JacksonObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationsConfig {

    @Value("${spring.kafka.topic.name}")
    private String topic;
    @Bean
    public NewTopic newTopic() {
        return new NewTopic(topic, 1, (short) 1);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return JacksonObjectMapper.getMapper();
    }
}