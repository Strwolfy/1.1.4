package Task;

import Task.dao.UserDaoJDBCImpl;
import Task.service.UserService;
import Task.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        //UserDaoJDBCImpl userService = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();
        // создание таблицы
        userService.createUsersTable();

        // добавление 4-х пользователей
        userService.saveUser("Alexey", "Trubov", (byte) 45);
        userService.saveUser("Oleg", "Sergeev", (byte) 32);
        userService.saveUser("Evgeniy", "Prigozhin", (byte) 53);
        userService.saveUser("Andrey", "Shevtsov", (byte) 27);

        // вывод всех пользователей
        userService.getAllUsers();

        // очистка таблицы
        userService.cleanUsersTable();

        //удаление таблицы
        userService.dropUsersTable();

    }
}
