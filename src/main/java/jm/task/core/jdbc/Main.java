package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService db = new UserServiceImpl();
        User user1 = new User("abc", "abc", (byte) 3);
        User user2 = new User("def", "def", (byte) 6);
        User user3 = new User("ghi", "ghi", (byte) 9);
        User user4 = new User("jkl", "jkl", (byte) 12);
        db.createUsersTable();
        db.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        db.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        db.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        db.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> test = db.getAllUsers();
        System.out.println(test.toString());
        db.cleanUsersTable();
        db.dropUsersTable();
    }
}