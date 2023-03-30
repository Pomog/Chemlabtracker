package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;
import java2.fitness_app.users.users.core.responses.LoginUserResponse;

import java.util.List;

public class LoginUserService {

    private Database database;
    private LoginUserValidator validator;

    public LoginUserService(Database database, LoginUserValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public LoginUserResponse execute(LoginUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LoginUserResponse(errors);
        }

        boolean isUserLogged = database.login(request.getUserIdToLogin(), request.getPassword());
        return new LoginUserResponse(isUserLogged);
    }

}