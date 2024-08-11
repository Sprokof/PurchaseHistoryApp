package generator.jdbc;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {
    private static final String DRIVER = System.getenv("DATABASE_DRIVER");
    private static final String URL = System.getenv("DATABASE_URL");
    private static final String USERNAME = System.getenv("DATABASE_USERNAME");
    private static final String PASSWORD =  System.getenv("DATABASE_PASSWORD");
    private static BlockingQueue<Connection> pool;
    private static final int POOL_SIZE = 10;
    private ConnectionManager () {
    }

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private static void initConnectionPool() {
        pool = new ArrayBlockingQueue<>(POOL_SIZE);
        for(int i = 0; i < POOL_SIZE; i ++) {
            Connection connection = open();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) ->
                            method.getName().equals("close") ?
                                    pool.add((Connection) proxy) :
                                    method.invoke(connection, args));

            pool.add(proxyConnection);
        }
    }

    private static Connection open() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Connection get() {
        try {
            return pool.take();
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

}
