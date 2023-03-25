package core.responses.shared;

import core.domain.user.User;
import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class SignInResponse extends CoreResponse {

    private User user;

    public SignInResponse(List<CoreError> errors) {
        super(errors);
    }

    public SignInResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
