package core.service;

import core.entities.User;
import core.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = {"${spring.kafka.topic.name}"}, groupId = "${spring.kafka.topic.group}")
    public void listenToTopic(String message) {
        User user = JsonUtil.readValue(message, User.class);
        userService.create(user);
    }

}
