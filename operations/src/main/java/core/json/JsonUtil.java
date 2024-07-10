package core.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import static core.json.JacksonObjectMapper.getMapper;

public class JsonUtil {
    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
