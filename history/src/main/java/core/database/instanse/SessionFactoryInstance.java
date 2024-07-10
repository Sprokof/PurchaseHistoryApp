package core.database.instanse;

import core.entities.PurchaseHistory;
import core.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    private static SessionFactory factory;
    private static final String CONFIG_FILE = "hibernate.cfg.xml";
    private static final Class<?>[] annotatedClasses = new Class[] {User.class, PurchaseHistory.class};
    public synchronized static SessionFactory instance() {
        if (factory == null) {
            factory = getSessionFactory();
        }
        return factory;
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        for (Class<?> c : annotatedClasses) {
            configuration.addAnnotatedClass(c);
        }
        return configuration.configure(CONFIG_FILE).buildSessionFactory();
    }
}
