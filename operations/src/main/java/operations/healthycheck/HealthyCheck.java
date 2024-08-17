package operations.healthycheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthyCheck {
    @GetMapping
    @RequestMapping("/operations/ping")
    public String ping() {
        return "healthy";
    }
}
