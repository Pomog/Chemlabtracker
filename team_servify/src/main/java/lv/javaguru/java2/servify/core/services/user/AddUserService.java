package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.user.AddUserRequest;
import lv.javaguru.java2.servify.core.responses.user.AddUserResponse;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {
    @Autowired private UsersDatabase userDB;
    @Autowired private AddUserValidator validator;

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
