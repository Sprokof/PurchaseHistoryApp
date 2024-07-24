package history.util;

import history.entities.Operation;
import history.entities.PurchaseHistory;
import history.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {
    private static final String DRIVER = System.getenv("DATABASE_DRIVER");
    private static final String URL = System.getenv("DATABASE_URL");
    private static final String USERNAME = System.getenv("DATABASE_USERNAME");
    private static final String PASSWORD =  System.getenv("DATABASE_PASSWORD");
    private static final String DIALECT = System.getenv("DATABASE_DIALECT");
    private static final Logger logger = Logger.getLogger(HibernateUtil.class.getSimpleName());
    private static final Class<?>[] ANNOTATED_CLASSES = new Class[] {User.class, PurchaseHistory.class, Operation.class};

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

    public static SessionFactory getSessionFactory() {
        SessionFactory factory = null;
        try {
            Configuration configuration = getConfiguration();
            for (Class<?> c : ANNOTATED_CLASSES) {
                configuration.addAnnotatedClass(c);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception was thrown", e);
        }
        return factory;
    }
}
