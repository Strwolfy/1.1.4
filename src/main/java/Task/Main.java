package Task;

import Task.service.UserService;
import Task.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Alexsandr", "Nikolaev", (byte) 19);
        userService.saveUser("Oleg", "Sergeev", (byte) 27);
        userService.saveUser("Vasiliy", "Petrov", (byte) 35);
        userService.saveUser("Lion", "Vaynov", (byte) 54);
        userService.getAllUsers();
        userService.cleanUsersTable();

    }
}
