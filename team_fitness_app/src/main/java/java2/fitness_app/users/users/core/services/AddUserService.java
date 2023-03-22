package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.User;
import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.AddUserRequest;
import java2.fitness_app.users.users.core.response.AddUserResponse;
import java2.fitness_app.users.users.core.response.CoreError;

import java.util.List;

public class AddUserService {

    private Database database;
    private AddUserValidator validator;

    public AddUserService(Database database) {
        this.database = database;
    }

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }

        User user = new User(request.getUsername(), request.getPassword());
        database.registerNewUser(user);
        return new AddUserResponse(user);
    }
}
