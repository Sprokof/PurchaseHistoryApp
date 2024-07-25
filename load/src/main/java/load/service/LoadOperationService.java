package load.service;

import operations.service.JsonService;
import load.util.HttpUtil;
import load.util.LoadOperationUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service
public class LoadOperationService implements CommandLineRunner {
    private static final String OPERATION_BASE_URL = "http://localhost:8080/operations";

    private final JsonService jsonService;

    public LoadOperationService(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < LoadOperationUtil.COUNT_OPERATIONS; i ++) {
            String operationJson = jsonService.writeValue(LoadOperationUtil.randomOperationDto());
            HttpUtil.post(OPERATION_BASE_URL, operationJson);
            if ((i + i) % LoadOperationUtil.COUNT_OPERATIONS_PER_SECOND == 0) {
                Thread.sleep(1000);
            }
        }
    }

}
