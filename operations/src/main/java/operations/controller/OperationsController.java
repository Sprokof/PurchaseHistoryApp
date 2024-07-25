package operations.controller;

import operations.dto.OperationDto;
import operations.service.JsonService;
import operations.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/operations")
public class OperationsController {

    private final KafkaProducer producer;

    private final JsonService jsonService;

    @Autowired
    public OperationsController(KafkaProducer producer, JsonService jsonService) {
        this.producer = producer;
        this.jsonService = jsonService;
    }

    @PostMapping
    public HttpStatus send(@RequestBody OperationDto dto) {
        producer.sendMessage(jsonService.writeValue(dto));
        return HttpStatus.CREATED;
    }
}
