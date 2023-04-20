package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.user.AddUserRequest;
import lv.javaguru.java2.servify.core.responses.user.AddUserResponse;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

@DIComponent
public class AddUserService {
    @DIDependency private UsersDatabase userDB;
    @DIDependency private AddUserValidator validator;

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }

        UserEntity user = new UserEntity(request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPhoneNumber());
        userDB.add(user);

        return new AddUserResponse(user);
    }
}
