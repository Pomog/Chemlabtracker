package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.requests.ValidateUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidateUserValidator {

    public List<CoreError> validate(ValidateUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(ValidateUserRequest request) {
        return (request.getUserIdToLogin() == null)
                ? Optional.of(new CoreError("id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(ValidateUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }

}
