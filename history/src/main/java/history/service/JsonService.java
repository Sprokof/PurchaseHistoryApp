package history.service;

public interface JsonService {
    <T> T readValue(String json, Class<?> aClass);
}
