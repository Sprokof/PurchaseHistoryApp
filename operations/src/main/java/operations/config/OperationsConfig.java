package operations.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.json.JacksonObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

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

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}
