package operations.service.jakcson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import operations.service.JsonService;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JacksonService implements JsonService {
    private static final Logger logger = Logger.getLogger(JacksonService.class.getSimpleName());

    private final ObjectMapper objectMapper;

    public JacksonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> String writeValue(T obj) {
        String result= null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "exception was thrown", e);
        }
        return result;
    }
}
