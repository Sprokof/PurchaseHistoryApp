package core.controller;

import core.dto.UserDto;
import core.json.JsonUtil;
import core.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class UserController {

    private final KafkaProducer producer;

    @Autowired
    public UserController(KafkaProducer producer) {
        this.producer = producer;
    }

    @GetMapping
    public String root() {
        return "send";
    }

    @PostMapping("/send")
    public void send() {
        producer.sendMessage(JsonUtil.writeValue(new UserDto("username", "password", LocalDate.of(2000, 2, 20))));
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
