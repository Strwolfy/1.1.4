package Task.dao;

import Task.model.User;
import Task.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;

    public UserDaoJDBCImpl() {
       connection = Util.getConnection();
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS mydbtest.users (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` TINYINT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
            statement.execute(SQL);
            System.out.println("Таблица создана");
            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Ошибка создания таблицы");
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL =" DROP TABLE IF EXISTS users;";
            statement.execute(SQL);
            System.out.println("Таблица удалена");
            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Ошибка удаления таблицы");
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String SQL = "INSERT mydbtest.users(name, lastName, age) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(SQL);

            System.out.println("Пользователь " + name +
                    " " + lastName + " был добавлен в базу данных");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(age));

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String SQL ="delete from mydbtest.users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Пользователь c Id: " + id + " удалён");
    }

    public List<User> getAllUsers() {

        System.out.println("Получаем всех пользователей...");

        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM mydbtest.users;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            String SQL = "TRUNCATE TABLE users";
            Statement statement = connection.createStatement();
            statement.execute(SQL);
            System.out.println("Таблица очищена");
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
