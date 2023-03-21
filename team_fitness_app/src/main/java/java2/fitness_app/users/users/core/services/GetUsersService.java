package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.requests.database.Database;
import java2.fitness_app.users.users.User;

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
