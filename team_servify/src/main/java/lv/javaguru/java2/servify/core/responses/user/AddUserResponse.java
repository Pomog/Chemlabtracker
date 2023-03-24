package lv.javaguru.java2.servify.core.responses.user;

import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.CoreResponse;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public class AddUserResponse extends CoreResponse {

    private UserEntity newUser;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddUserResponse(UserEntity newUser) {
        this.newUser = newUser;
    }

    public UserEntity getNewUser() {
        return newUser;
    }
}
