package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.responses.RemoveUserResponse;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.core.requests.RemoveUserRequest;

import java.util.List;

public class RemoveUserService {

    private Database database;
    private RemoveUserValidator validator;

    public RemoveUserService(Database database, RemoveUserValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserRemoved = false;
        if (!errors.isEmpty()) {
            return new RemoveUserResponse(errors);
        } else if (database.findUserById(request.getUserId()).isPresent()) {
            User user = database.findUserById(request.getUserId()).get();
            if (user.getId().equals(request.getUserId()) && user.getPassword().equals(request.getPassword())) {
                database.deleteUser(user);
                isUserRemoved = true;
            }
        }
        return new RemoveUserResponse(isUserRemoved);
    }
}