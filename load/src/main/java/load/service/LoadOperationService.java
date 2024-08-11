package load.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import load.util.HttpUtil;
import load.util.LoadOperationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoadOperationService  {
    private static final String OPERATION_BASE_URL = "http://operations-app:8080/operations";
    private static final Logger logger = LoggerFactory.getLogger(LoadOperationService.class.getSimpleName());
    private final ObjectMapper mapper;
    public LoadOperationService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);
    public void run() throws Exception {
        for (int i = 0; i < LoadOperationUtil.COUNT_OPERATIONS; i += LoadOperationUtil.BATCH_SIZE) {
            List<Future<HttpResponse<String>>> requestsResults = new ArrayList<>();
            for (int j = i; j < (i + LoadOperationUtil.BATCH_SIZE); j ++) {
                String request = mapper.writeValueAsString(LoadOperationUtil.randomOperationDto());
                Future<HttpResponse<String>> result = executorService.submit(() -> HttpUtil.post(OPERATION_BASE_URL, request));
                requestsResults.add(result);
            }
            requestsResults.forEach(result -> {
                try {
                    var response = result.get();
                    if (response != null) {
                        logger.info("body {} status code {}", response.body(), response.statusCode());
                    } else {
                        logger.warn("wrong result");
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
