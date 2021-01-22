package Task.dao;

import Task.model.User;
import Task.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.management.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory factory = HibernateUtil.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS mydbtest.users (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";

        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(SQL);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL =" DROP TABLE IF EXISTS users;";

        try (Session session = factory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.createSQLQuery(SQL);
            System.out.println("Таблица удалена");
            transaction.commit();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String SQL = "INSERT mydbtest.users(name, lastName, age) VALUES(?, ?, ?)";
        try (Session session = factory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);

            System.out.println("Пользователь " + name +
                    " " + lastName + " был добавлен в базу данных");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("Пользователь c Id: " + id + " удалён");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            String sql = "SELECT * FROM mydbtest.users";
            session.beginTransaction();
            List<User> list = (List<User>) session.createSQLQuery(sql).list();
            list.forEach(System.out::println);
            return list;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
