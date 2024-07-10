package core.repository.hibernate;

import core.database.instanse.SessionFactoryInstance;
import core.entities.User;
import core.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryHibernate implements UserRepository {
    private final SessionFactory sessionFactory = SessionFactoryInstance.instance();

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
        e.printStackTrace();
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
