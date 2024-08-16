package operations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topic;

    private final KafkaTemplate<String, String> template;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void sendMessage(String message) {
        this.template.send(topic, message);
    }

}
