package Task.service;

import Task.dao.UserDaoJDBCImpl;
import Task.model.User;

import java.util.List;

public class UserServiceImpl implements UserService  {

    UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        daoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        daoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        daoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        daoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return daoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        daoJDBC.cleanUsersTable();
    }
}