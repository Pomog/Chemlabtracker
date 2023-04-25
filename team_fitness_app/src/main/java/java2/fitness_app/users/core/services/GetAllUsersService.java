package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.requests.GetAllUsersRequest;
import java2.fitness_app.users.core.responses.GetAllUsersResponse;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.dependency_injection.DIComponent;
import java2.fitness_app.users.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class GetAllUsersService {

    @DIDependency
    private Database database;


    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = database.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
