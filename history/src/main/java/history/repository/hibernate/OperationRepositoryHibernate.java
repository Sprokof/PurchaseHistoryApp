package history.repository.hibernate;

import history.entities.Operation;
import history.repository.OperationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class OperationRepositoryHibernate implements OperationRepository {
    private static final Logger logger = LoggerFactory.getLogger(OperationRepositoryHibernate.class.getSimpleName());
    private final SessionFactory sessionFactory;

    public OperationRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Operation create(Operation operation) {
        Session session = null;
        Long id = null;
        try {
            logger.info("session open");
            session = this.sessionFactory.openSession();
            logger.info("begin transaction");
            session.beginTransaction();
            id = (Long) session.save(operation);
            session.getTransaction().commit();
            logger.info("commit transaction");
        }
        catch (Exception e) {
            logger.error("exception was thrown ", e);
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
                logger.info("transaction rollback");
            }
        }
        finally {
            if (session != null) {
                session.close();
                logger.info("session close");
            }
        }
        operation.setId(id);
        return operation;
    }
}
