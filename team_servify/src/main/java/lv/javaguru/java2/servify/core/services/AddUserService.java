package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.AddUserRequest;
import lv.javaguru.java2.servify.core.responses.AddUserResponse;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public class AddUserService {
    private UsersDatabase usersDatabase;
    private AddUserValidator validator;

    public AddUserService(UsersDatabase usersDatabase,
                          AddUserValidator validator) {
        this.usersDatabase = usersDatabase;
        this.validator = validator;
    }

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }

        UserEntity user = new UserEntity(request.getFirstName(), request.getSecondName(),
                request.getEmail(), request.getPhoneNumber());
        usersDatabase.add(user);

        return new AddUserResponse(user);
    }
}
