package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.requests.AddUserRequest;
import java2.fitness_app.users.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddUserValidator {
    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUsername(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUsername(AddUserRequest request) {
        return (request.getUsername() == null || request.getUsername().isBlank())
                ? Optional.of(new CoreError("username", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }
}
