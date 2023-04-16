package shop.core.responses.shared;

import shop.core.domain.user.User;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

public class SignInResponse extends CoreResponse {

    private User user;

    public SignInResponse(User user) {
        this.user = user;
    }

    public SignInResponse(List<CoreError> errors) {
        super(errors);
    }

    public User getUser() {
        return user;
    }

}
