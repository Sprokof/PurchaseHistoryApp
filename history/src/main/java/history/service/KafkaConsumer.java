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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@EnableKafka
@EnableScheduling
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class.getSimpleName());
    private final PurchaseHistoryService purchaseHistoryService;
    private final ObjectMapper mapper;
    public static final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    public KafkaConsumer(PurchaseHistoryService purchaseHistoryService, ObjectMapper mapper) {
        this.purchaseHistoryService = purchaseHistoryService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = {"${spring.kafka.topic.name}"}, groupId = "${spring.kafka.topic.group}")
    public void listenToTopic(String message) {
        try {
            logger.info("message from topic operations:" + message);
            OperationDto operationDto = mapper.readValue(message, OperationDto.class);
            boolean added = this.purchaseHistoryService.addOperation(operationDto);
            logger.info("added: {}", added);
            counter.incrementAndGet();
        } catch (JsonProcessingException | UnknownOperationException e) {
            logger.error("exception was thrown", e);
        }
    }
    @Scheduled(fixedRate = (30 * 1000))
    private static void task() {
        logger.info("count {}", counter.intValue());
    }
}
