package Task.util;

import Task.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC?";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties setting = new Properties();
            // замена XML  настроек
            setting.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            setting.put(Environment.URL, URL);
            setting.put(Environment.USER, USER);
            setting.put(Environment.PASS, PASSWORD);
            setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            setting.put(Environment.SHOW_SQL, "true");

            setting.put(Environment.HBM2DDL_AUTO, "create-drop");
            setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration.setProperties(setting);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
