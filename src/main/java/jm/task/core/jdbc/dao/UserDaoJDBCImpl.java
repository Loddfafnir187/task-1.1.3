package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {


        try (Connection conn = Util.getMySQLConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users ( ID MEDIUMINT NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(20) NOT NULL, " +
                    " lastName VARCHAR(20) NOT NULL, " +
                    " age TINYINT(20) NOT NULL," +
                    " PRIMARY KEY (ID));");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void dropUsersTable() {


        try (Connection conn = Util.getMySQLConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO Users (name, lastName, age) values(?, ?, ?)";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setString(1, name);
            preStatement.setString(2, lastName);
            preStatement.setByte(3, age);
            preStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("User с именем " + name + " добавлен в базу.");
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM Users WHERE id = ?";
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setLong(1, id);
            preStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection();
             Statement statement = conn.createStatement();
             ResultSet res = statement.executeQuery("select * from Users")) {

            while (res.next()) {
                allUsers.add(new User(res.getLong("ID"), res.getString("name"),
                        res.getString("lastName"), res.getByte("age")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allUsers;
    }

    public void cleanUsersTable() {

        try (Connection conn = Util.getMySQLConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
