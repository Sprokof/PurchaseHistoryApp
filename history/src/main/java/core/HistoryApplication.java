package core;
import core.entities.User;
import core.json.JsonUtil;
import core.service.KafkaConsumer;
import core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
public class HistoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(HistoryApplication.class);
    }

}