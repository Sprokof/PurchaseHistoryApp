package load.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import load.util.HttpUtil;
import load.util.LoadOperationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class LoadOperationService implements CommandLineRunner {
    private static final String OPERATION_BASE_URL = "http://operations-app:8080/operations";
    private static final Logger logger = LoggerFactory.getLogger(LoadOperationService.class.getSimpleName());
    private final ObjectMapper mapper;
    public LoadOperationService(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < LoadOperationUtil.COUNT_OPERATIONS; i ++) {
            try {
                String operationJson = mapper.writeValueAsString(LoadOperationUtil.randomOperationDto());
                logger.info("start request");
                HttpUtil.post(OPERATION_BASE_URL, operationJson);
                logger.info("end request");
                if ((i + i) % LoadOperationUtil.COUNT_OPERATIONS_PER_SECOND == 0) {
                    Thread.sleep(10000);
                }
            } catch (JsonProcessingException e) {
                logger.error("exception was thrown", e);
            }
        }
        logger.info("all request end. count = " + LoadOperationUtil.COUNT_OPERATIONS);
    }

}
