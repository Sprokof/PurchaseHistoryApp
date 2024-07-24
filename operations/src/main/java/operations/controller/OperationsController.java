package operations.controller;

import operations.dto.OperationDto;
import operations.service.JsonService;
import operations.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

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
    public HttpStatus send(@RequestParam("sum") Double sum, @RequestParam("type") String type, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        OperationDto operationDto = new OperationDto(sum, type, date);
        producer.sendMessage(jsonService.writeValue(operationDto));
        return HttpStatus.CREATED;
    }
}
