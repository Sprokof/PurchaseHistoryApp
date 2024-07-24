package history.service;

import history.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {
    private final OperationService operationService;
    private final JsonService jsonService;
    @Autowired
    public KafkaConsumer(OperationService operationService, JsonService jsonService) {
        this.operationService = operationService;
        this.jsonService = jsonService;
    }

    @KafkaListener(topics = {"${spring.kafka.topic.name}"}, groupId = "${spring.kafka.topic.group}")
    public void listenToTopic(String message) {
        Operation operation = jsonService.readValue(message, Operation.class);
        operationService.create(operation);
    }

}
