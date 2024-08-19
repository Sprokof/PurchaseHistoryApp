package history.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.json.JacksonObjectMapper;
import history.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoryConfig {
    @Bean
    public SessionFactory sessionFactory() {
       return HibernateUtil.getSessionFactory();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonObjectMapper.getMapper();
    }
}
