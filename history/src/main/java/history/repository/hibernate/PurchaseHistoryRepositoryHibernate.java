package history.repository.hibernate;

import history.entities.Operation;
import history.entities.PurchaseHistory;
import history.repository.PurchaseHistoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryRepositoryHibernate implements PurchaseHistoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseHistoryRepositoryHibernate.class);

    private final SessionFactory sessionFactory;

    public PurchaseHistoryRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addOperation(Operation operation, long userId) {
        Session session = null;
        try {
            logger.info("open session");
            session = this.sessionFactory.getCurrentSession();
            logger.info("begin transaction");
            session.beginTransaction();
            logger.info("get purchase history");
            PurchaseHistory history = session.createQuery("SELECT p FROM PurchaseHistory p LEFT JOIN FETCH p.operations JOIN FETCH p.user u WHERE u.id = :id", PurchaseHistory.class)
                            .setParameter("id", userId)
                            .getSingleResult();
            logger.info("add operation");
            history.addOperation(operation);
            logger.info("update history");
            session.update(history);
            logger.info("update user balance");
            session.createQuery("UPDATE User u SET u.balance = u.balance - :sum WHERE u.id = :id")
                            .setParameter("sum", operation.getSum())
                    .setParameter("id", userId)
                    .executeUpdate();
            logger.info("commit transaction");
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("exception was thrown", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("transaction rollback");
                return false;
            }
        }
        return true;
    }
}
