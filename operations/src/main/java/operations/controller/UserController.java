package operations.controller;

import json.util.JsonUtil;
import operations.dto.UserDto;
import operations.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class UserController {

    private final KafkaProducer producer;

    @Autowired
    public UserController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public HttpStatus send(@RequestParam("u") String name, @RequestParam("p")  String password, @RequestParam("bd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        UserDto userDto = new UserDto(name, password, date);
        producer.sendMessage(JsonUtil.writeValue(userDto));
        return HttpStatus.CREATED;
    }
}
