package users.core.services;

import users.User;
import users.core.requests.database.Database;

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
