package Task;

import Task.dao.UserDaoJDBCImpl;
import Task.service.UserService;
import Task.service.UserServiceImpl;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Alexey", "Trubov", (byte) 45);
        userService.saveUser("Oleg", "Sergeev", (byte) 32);
        userService.saveUser("Evgeniy", "Prigozhin", (byte) 53);
        userService.saveUser("Andrey", "Shevtsov", (byte) 27);
        userService.getAllUsers();
        userService.cleanUsersTable();

    } 
}
