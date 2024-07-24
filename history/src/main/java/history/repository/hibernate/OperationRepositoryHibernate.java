package history.repository.hibernate;

import history.entities.Operation;
import history.entities.User;
import history.repository.OperationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class OperationRepositoryHibernate implements OperationRepository {
    private static final Logger logger = Logger.getLogger(OperationRepositoryHibernate.class.getSimpleName());
    private final SessionFactory sessionFactory;

    public OperationRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Operation create(Operation operation) {
        Session session = null;
        Long id = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            id = (Long) session.save(operation);
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
        operation.setId(id);
        return operation;
    }
}
