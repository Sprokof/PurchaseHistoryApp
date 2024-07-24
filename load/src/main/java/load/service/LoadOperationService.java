package load.service;

import load.util.HttpUtil;
import load.util.LoadOperationUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service
public class LoadOperationService implements CommandLineRunner {

    private static final String OPERATION_BASE_URI = "http://localhost:8080/operations";

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < LoadOperationUtil.COUNT_OPERATIONS; i ++) {
            String sendOperationUrl = OPERATION_BASE_URI + "?sum=" + LoadOperationUtil.getPriceQueryParam() +
                    "&type=" + LoadOperationUtil.getTypeQueryParam() +
                    "&date=" + LoadOperationUtil.getDateQueryParam();
            HttpUtil.postWithoutBody(sendOperationUrl);
            if ((i + 1) == LoadOperationUtil.COUNT_OPERATIONS_PER_SECOND) {
                Thread.sleep(1000);
            }
        }
    }

}
