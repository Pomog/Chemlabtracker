package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveUserValidator {
    public List<CoreError> validate (RemoveUserRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(RemoveUserRequest request){
        return (request.getUserIdToRemove() == null || request.getUserIdToRemove().toString().isBlank())
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(RemoveUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }
}
