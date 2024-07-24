package generator;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"history.service"})
public class GeneratorApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}