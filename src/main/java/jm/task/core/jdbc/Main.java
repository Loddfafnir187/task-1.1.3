package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService db = new UserServiceImpl();
        User user1 = new User("Abc", "Abc", (byte) 2);
        User user2 = new User("Def", "Def", (byte) 4);
        User user3 = new User("Ghi", "Ghi", (byte) 6);
        User user4 = new User("Jkl1", "Jkl", (byte) 8);
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
