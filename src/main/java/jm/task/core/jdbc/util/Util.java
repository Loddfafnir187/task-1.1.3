package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public static Connection getMySQLConnection() {
        String hostName = "localhost";

        String dbName = "temp";
        String userName = "root";
        String password = "JavaSql";

        try {
            return getMySQLConnection(hostName, dbName, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return null;
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;


        return DriverManager.getConnection(connectionURL, userName,
                password);
    }

// реализуйте настройку соеденения с БД
}
