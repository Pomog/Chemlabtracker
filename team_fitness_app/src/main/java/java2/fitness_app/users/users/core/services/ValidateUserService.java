package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.ValidateUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;
import java2.fitness_app.users.users.core.responses.ValidateUserResponse;

import java.util.List;

public class ValidateUserService {

    private Database database;
    private ValidateUserValidator validator;

    public ValidateUserService(Database database, ValidateUserValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public ValidateUserResponse execute(ValidateUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ValidateUserResponse(errors);
        }

        boolean isUserValidated = database.login(request.getUserIdToLogin(), request.getPassword());
        return new ValidateUserResponse(isUserValidated);
    }

}