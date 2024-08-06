package operations.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.dto.OperationDto;
import operations.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/operations")
public class OperationsController {
    private static final Logger logger = LoggerFactory.getLogger(OperationsController.class.getSimpleName());
    private final KafkaProducer producer;
    private final ObjectMapper mapper;

    @Autowired
    public OperationsController(KafkaProducer producer, ObjectMapper mapper) {
        this.producer = producer;
        this.mapper = mapper;
    }

    @GetMapping("/ping")
    public String ping() {
        return "healthy";
    }

    @PostMapping(consumes = {"application/json"})
    public HttpStatus send(@RequestBody OperationDto dto) {
        try {
            String message = mapper.writeValueAsString(dto);
            logger.info("send message " + message);
            producer.sendMessage(message);
        } catch (JsonProcessingException e) {
            logger.error("exception was thrown", e);
        }
        return HttpStatus.CREATED;
    }
}
