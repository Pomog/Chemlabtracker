package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.responses.RemoveUserResponse;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.dependency_injection.DIComponent;
import java2.fitness_app.users.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class RemoveUserService {

    @DIDependency
    private Database database;
    @DIDependency
    private RemoveUserRequestValidator validator;


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