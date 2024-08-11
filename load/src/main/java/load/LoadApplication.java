package load;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.json.JacksonObjectMapper;
import load.service.LoadOperationService;

public class LoadApplication {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        LoadOperationService loadOperationService = new LoadOperationService(mapper);
        loadOperationService.run();
    }
}