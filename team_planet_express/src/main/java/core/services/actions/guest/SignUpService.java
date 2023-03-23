package core.services.actions.guest;

import core.support.MutableLong;

public class SignUpService {

    private final MutableLong currentUserId;

    public SignUpService(MutableLong currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void execute() {
        //TODO register a new user
    }

}
