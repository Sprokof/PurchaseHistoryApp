package load.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.dto.OperationDto;
import load.helper.LoadOperationHelper;
import load.statistics.UserBalanceStatistics;
import load.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class LoadOperationService implements CommandLineRunner {
    private static final String OPERATION_BASE_URL = "http://operations-app:8080/operations";
    private static final Logger logger = LoggerFactory.getLogger(LoadOperationService.class.getSimpleName());
    private final LoadOperationHelper operationHelper;
    private final ObjectMapper mapper;
    public LoadOperationService(LoadOperationHelper operationHelper, ObjectMapper mapper) {
        this.operationHelper = operationHelper;
        this.mapper = mapper;
    }
    private final ExecutorService executorService = Executors.newFixedThreadPool(100);
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < LoadOperationHelper.COUNT_OPERATIONS; i += LoadOperationHelper.BATCH_SIZE) {
            List<Future<Boolean>> requestsResults = new ArrayList<>();
            for (int j = i; j < (i + LoadOperationHelper.BATCH_SIZE); j ++) {
                OperationDto dto = this.operationHelper.randomOperationDto();
                String request = this.mapper.writeValueAsString(dto);
                Future<Boolean> result = this.executorService.submit(() -> {
                    var response = HttpUtil.post(OPERATION_BASE_URL, request);
                    if (response != null && response.statusCode() == 200) {
                        logger.info("body {} status code {}", response.body(), response.statusCode());
                        UserBalanceStatistics.addOperation(dto);
                        return true;
                    } else {
                        logger.warn("wrong result");
                        return false;
                    }
                });
                requestsResults.add(result);
            }
            requestsResults.forEach(result -> {
                try {
                    result.get();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        logger.info(UserBalanceStatistics.getAll());
        System.exit(0);
    }
}
