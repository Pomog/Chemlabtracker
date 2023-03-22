package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;

public class ValidateUserService {

    private Database database;

    public ValidateUserService(Database database) {
        this.database = database;
    }

    public boolean execute(Long id, String password) {
        return database.login(id, password);
    }
}