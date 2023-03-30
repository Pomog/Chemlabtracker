package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginUserValidator {

    public List<CoreError> validate(LoginUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(LoginUserRequest request) {
        return (request.getUserIdToLogin() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(LoginUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }

}
