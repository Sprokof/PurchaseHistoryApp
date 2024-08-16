package history.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.dto.OperationDto;
import core.exceptions.UnknownOperationException;
import history.entities.Operation;
import history.entities.User;
import history.repository.hibernate.OperationRepositoryHibernate;
import history.util.OperationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class.getSimpleName());
    private final OperationService operationService;
    private final UserService userService;
    private final ObjectMapper mapper;

    @Autowired
    public KafkaConsumer(OperationService operationService, UserService userService, ObjectMapper mapper) {
        this.operationService = operationService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = {"${spring.kafka.topic.name}"}, groupId = "${spring.kafka.topic.group}")
    public void listenToTopic(String message) {
        try {
            logger.info("message from topic operations:" + message);
            OperationDto operationDto = mapper.readValue(message, OperationDto.class);
            long userId = operationDto.getUserId();
            User user = this.userService.getWithPurchaseHistoryAndOperations(userId);
            Operation operation = OperationUtil.fromDto(operationDto);
            user.getPurchaseHistory().addOperation(operation);
            this.userService.update(user);
        } catch (JsonProcessingException | UnknownOperationException e) {
            logger.error("exception was thrown", e);
        }
    }
}
