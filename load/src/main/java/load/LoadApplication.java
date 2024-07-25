package load;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"operations.service"})
public class LoadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadApplication.class);
    }
}