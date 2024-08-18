package history.repository.hibernate;

import history.entities.Operation;
import history.entities.User;
import history.repository.UserRepository;
import history.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.EntityGraphs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import java.util.List;
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
        List<Operation> operations = null;
        try {
            session = this.sessionFactory.openSession();
            logger.info("open session");
            session.beginTransaction();
            logger.info("begin transaction");
            user = session.createQuery("SELECT u FROM User u JOIN FETCH u.purchaseHistory WHERE u.id =:id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            operations = session.createQuery("SELECT o FROM Operation o WHERE o.purchaseHistory.user.id =:id", Operation.class)
                    .setParameter("id", id)
                    .list();
            session.getTransaction().commit();
            logger.info("commit transaction");
        } catch (Exception e) {
            logger.error("exception was thrown", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("rollback transaction");
            }
        }
        finally {
            if (session != null) {
                session.close();
                logger.info("session close");
            }
        }
        if (user != null) {
            user.getPurchaseHistory().setOperations(operations);
        }
        return user;
    }

}
