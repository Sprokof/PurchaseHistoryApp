package history.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.json.JacksonObjectMapper;
import history.repository.OperationRepository;
import history.repository.UserRepository;
import history.repository.hibernate.OperationRepositoryHibernate;
import history.repository.hibernate.UserRepositoryHibernate;
import history.service.OperationService;
import history.service.UserService;
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
    public UserRepository userRepository() {
        return new UserRepositoryHibernate(sessionFactory());
    }
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public OperationRepository operationRepository() {
        return new OperationRepositoryHibernate(sessionFactory());
    }

    @Bean
    public OperationService operationService() {
        return new OperationService(operationRepository());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonObjectMapper.getMapper();
    }
}
