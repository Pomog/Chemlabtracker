package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.domain.User;
import java2.fitness_app.users.users.core.requests.GetAllUsersRequest;
import java2.fitness_app.users.users.core.responses.GetAllUsersResponse;

import java.util.List;

public class GetAllUsersService {

    private Database database;

    public GetAllUsersService(Database database) {
        this.database = database;
    }

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = database.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
