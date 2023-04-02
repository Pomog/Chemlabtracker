package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.domain.User;
import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.AddUserRequest;
import java2.fitness_app.users.users.core.responses.AddUserResponse;
import java2.fitness_app.users.users.core.responses.CoreError;

import java.util.List;

public class AddUserService {

    private Database database;
    private AddUserValidator validator;

    public AddUserService(Database database, AddUserValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }else{
            User user = new User(request.getUsername(), request.getPassword());
            database.registerNewUser(user);
            return new AddUserResponse(user);
        }
    }
}
