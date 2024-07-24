package history.service.jakson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import history.entities.BaseEntity;
import history.service.JsonService;
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
    public <T> T readValue(String json, Class<?> c) {
        T result = null;
        try {
            result = (T) this.objectMapper.readValue(json, c);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "exception was thrown", e);
        }
        return result;
    }
}
