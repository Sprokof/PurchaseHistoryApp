package history.repository.hibernate.instanse;

import history.entities.PurchaseHistory;
import history.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.*;

import java.util.Properties;

public class SessionFactoryInstance {
    private static final String DRIVER = System.getenv("database.driver");
    private static final String URL = System.getenv("database.url");
    private static final String USERNAME = System.getenv("database.username");
    private static final String PASSWORD =  System.getenv("database.password");
    private static final String DIALECT = System.getenv("database.dialect");
    private static final Logger logger = Logger.getLogger("sessionFactoryInstance");
    private static SessionFactory factory;
    private static final Class<?>[] annotatedClasses = new Class[] {User.class, PurchaseHistory.class};

    public static SessionFactory instance() {
        if (factory == null) {
            try {
                Configuration configuration = getConfiguration();
                for (Class<?> c : annotatedClasses) {
                    configuration.addAnnotatedClass(c);
                }

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                factory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "exception was thrown" , e);
            }
        }
        return factory;
    }
    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, DRIVER);
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, DIALECT);
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        configuration.setProperties(settings);
        return configuration;
    }
}
