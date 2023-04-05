package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.responses.LoginUserResponse;

import java.util.List;

public class LoginUserService {

    private Database database;
    private LoginUserRequestValidator validator;

    public LoginUserService(Database database, LoginUserRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public LoginUserResponse execute(LoginUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserLogged = false;
        if (!errors.isEmpty()) {
            return new LoginUserResponse(errors);
        } else if (database.findUserById(request.getUserIdToLogin()).isPresent()) {
            User user = database.findUserById(request.getUserIdToLogin()).get();
            if (user.getId().equals(request.getUserIdToLogin()) && user.getPassword().equals(request.getPassword())) {
                isUserLogged = true;
            }
        }
        return new LoginUserResponse(isUserLogged);
    }

}