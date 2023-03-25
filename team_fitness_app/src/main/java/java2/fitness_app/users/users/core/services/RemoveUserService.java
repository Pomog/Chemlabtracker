package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;
import java2.fitness_app.users.users.core.responses.RemoveUserResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoveUserService {

    private Database database;

    public RemoveUserService(Database database) {
        this.database = database;
    }

    public RemoveUserResponse execute(RemoveUserRequest request) {
        if (request.getUserIdToRemove() == null) {
            CoreError error = new CoreError("id", "id is required");
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new RemoveUserResponse(errors);
        }
        boolean isUserRemoved = database.deleteUser(request.getUserIdToRemove(), request.getPassword());
        return new RemoveUserResponse(isUserRemoved);
    }

}
