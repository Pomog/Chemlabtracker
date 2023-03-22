package core.services.actions.guest;

import core.domain.user.User;

public class SignUpService {

    private final User user;

    public SignUpService(User user) {
        this.user = user;
    }

    public void execute() {
        //TODO register a new user
    }

}
