package Task.dao;

import Task.model.User;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age) throws SQLException;

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers();

    void cleanUsersTable() throws SQLException;
}
