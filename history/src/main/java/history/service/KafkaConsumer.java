package history.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import history.entities.Operation;
import history.repository.hibernate.OperationRepositoryHibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OperationRepositoryHibernate.class.getSimpleName());
    private final OperationService operationService;
    private final ObjectMapper mapper;

    @Autowired
    public KafkaConsumer(OperationService operationService, ObjectMapper mapper) {
        this.operationService = operationService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = {"${spring.kafka.topic.name}"}, groupId = "${spring.kafka.topic.group}")
    public void listenToTopic(String message) {
        try {
            Operation operation = mapper.readValue(message, Operation.class);
            operationService.create(operation);
        } catch (JsonProcessingException e) {
            logger.error("exception was thrown", e);
        }
    }
}
