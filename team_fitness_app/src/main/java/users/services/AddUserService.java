package users.services;

import users.User;
import users.database.Database;

public class AddUserService {

    private Database database;

    public AddUserService(Database database) {
        this.database = database;
    }

    public void execute(String username, String password) {
        User user = new User(username, password);
        database.registerNewUser(user);
        System.out.println("users.User registered with user ID " + user.getId());
    }
}
