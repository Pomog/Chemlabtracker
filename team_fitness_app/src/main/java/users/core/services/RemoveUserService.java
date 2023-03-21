package users.core.services;

import users.core.requests.database.Database;

public class RemoveUserService {

    private Database database;

    public RemoveUserService(Database database) {
        this.database = database;
    }

    public boolean execute(Long id, String password) {
        return database.deleteUser(id, password);
    }

}
