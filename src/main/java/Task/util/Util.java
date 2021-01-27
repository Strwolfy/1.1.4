package Task.util;

import Task.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            properties.setProperty(Environment.USER, USER);
            properties.setProperty(Environment.PASS, PASSWORD);
            properties.setProperty(Environment.URL, DATABASE_URL);

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class); // добавить entity class

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory;
    }
}