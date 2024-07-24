package history.repository.hibernate;

import history.entities.User;
import history.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserRepositoryHibernate implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserRepositoryHibernate.class.getSimpleName());
    private final SessionFactory sessionFactory;
    public UserRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User create(User user) {
        Session session = null;
        Long id = null;
    try {
        session = this.sessionFactory.openSession();
        session.beginTransaction();
        id = (Long) session.save(user);
        session.getTransaction().commit();
    }
    catch (Exception e) {
        logger.log(Level.SEVERE, "exception was thrown ", e);
        if (session != null && session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
    }
    finally {
        if (session != null) {
            session.close();
        }
    }
    user.setId(id);
    return user;
    }
}
