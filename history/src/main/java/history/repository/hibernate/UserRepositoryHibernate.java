package history.repository.hibernate;

import history.entities.User;
import history.repository.UserRepository;
import history.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class UserRepositoryHibernate implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryHibernate.class.getSimpleName());
    private final SessionFactory sessionFactory;

    public UserRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User create(User user) {
        Session session = null;
        Long id = null;
        try {
            logger.info("open session");
            session = this.sessionFactory.openSession();
            logger.info("begin transaction");
            session.beginTransaction();
            id = (Long) session.save(user);
            session.getTransaction().commit();
            logger.info("commit transaction");
        } catch (Exception e) {
            logger.error("exception was thrown ", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("rollback transaction");
            }
        } finally {
            if (session != null) {
                session.close();
                logger.info("close session");
            }
        }
        user.setId(id);
        return user;
    }

    @Override
    public void update(User user) {
        Session session = null;
        try {
            logger.info("open session");
            session = this.sessionFactory.openSession();
            logger.info("begin transaction");
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            logger.info("commit transaction");
        } catch (Exception e) {
            logger.error("exception was thrown ", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("rollback transaction");
            }
        } finally {
            if (session != null) {
                session.close();
                logger.info("close session");
            }
        }
    }

    @Override
    public User getById(long id) {
        Session session = null;
        User user = null;
        try {
            logger.info("open session");
            session = this.sessionFactory.openSession();
            logger.info("begin transaction");
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
            logger.info("commit transaction");
        } catch (Exception e) {
            logger.error("exception wash thrown", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("rollback transaction");
            }
        } finally {
            if (session != null) {
                session.close();
                logger.info("close session");
            }
        }
        return user;
    }

    @Override
    public User getWithPurchaseHistoryAndOperations(long id) {
        Session session = null;
        User user = null;
        try {
            logger.info("open session");
            session = this.sessionFactory.openSession();
            logger.info("begin transaction");
            session.beginTransaction();
            user = session.createQuery("SELECT User FROM User as u JOIN " +
                            "FETCH u.purchaseHistory JOIN FETCH u.purchaseHistory.operations", User.class)
                    .getSingleResult();
            session.getTransaction().commit();
            logger.info("commit transaction");
        } catch (Exception e) {
            logger.error("exception wash thrown", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("rollback transaction");
            }
        } finally {
            if (session != null) {
                session.close();
                logger.info("close session");
            }
        }
        return user;
    }
}
