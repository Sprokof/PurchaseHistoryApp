package history.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class SqlHelper implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SqlHelper.class.getSimpleName());
    private final SessionFactory sessionFactory;

    public SqlHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void executeSqlScript(String sql) {
        Session session = null;
        try {
            logger.info("open session");
            session = this.sessionFactory.openSession();
            logger.info("begin transaction");
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            logger.info("commit transaction");
            session.getTransaction().commit();
        } catch (Exception e) {
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
    private String readSqlScript() {
        StringBuilder output = new StringBuilder();
        try(InputStream stream = SqlHelper.class.getClassLoader().getResourceAsStream("initDb.sql")) {
            int c =  0;
            while((c = stream.read()) != -1) {
                output.append((char) c);
            }
        } catch (IOException e) {
            logger.info("exception was thrown ", e);
        }
        return output.toString();
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = readSqlScript();
        executeSqlScript(sql);
    }
}
