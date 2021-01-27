package Task.dao;

import Task.model.User;
import Task.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory factory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS mydbtest.users (\n" +
                "  `id`  INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            session.beginTransaction();
            session.createSQLQuery(SQL);
            System.out.println("Таблица создана");
            session.getTransaction().commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL ="DROP TABLE IF EXISTS users;";
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery(SQL);
            System.out.println("Таблица удалена");
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);

            System.out.println("Пользователь " + name +
                    " " + lastName + " был добавлен в базу данных");
            session.getTransaction().commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("Пользователь c Id: " + id + " удалён");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        try  {
            String sql = "SELECT * FROM mydbtest.users";
            session.beginTransaction();
            List<User> list = (List<User>) session.createQuery("from User").list();
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = factory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            System.out.println("Таблица очищена");
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
