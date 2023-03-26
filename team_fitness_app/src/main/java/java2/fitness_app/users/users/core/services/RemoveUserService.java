package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;
import java2.fitness_app.users.users.core.responses.RemoveUserResponse;

import java.util.ArrayList;
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
        if(!errors.isEmpty()){
            return new RemoveUserResponse(errors);
        }

        boolean isUserRemoved = database.deleteUser(Long.parseLong(request.getUserIdToRemove()), request.getPassword());
        return new RemoveUserResponse(isUserRemoved);
    }










}
