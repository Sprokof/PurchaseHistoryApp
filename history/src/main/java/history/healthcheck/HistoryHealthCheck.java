package history.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryHealthCheck {

    @GetMapping("/history/ping")
    public String ping() {
        return "ping";
    }
}
