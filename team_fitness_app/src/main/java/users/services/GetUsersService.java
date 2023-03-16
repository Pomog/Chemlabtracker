package users.services;

import users.User;
import users.database.Database;

import java.util.List;

public class GetUsersService {

    private Database database;

    public GetUsersService(Database database) {
        this.database = database;
    }

    public List<User> execute() {
        return database.getUsers();
    }
}
