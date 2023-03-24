package lv.javaguru.java2.servify.core.responses.detail;

import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.CoreResponse;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public class AddDetailResponse extends CoreResponse {

    private UserEntity newUser;

    public AddDetailResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddDetailResponse(UserEntity newUser) {
        this.newUser = newUser;
    }

    public UserEntity getNewUser() {
        return newUser;
    }
}
