package core.services.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.shared.SignOutRequest;
import core.responses.shared.SignOutResponse;

public class SignOutService {

    private final Database database;

    public SignOutService(Database database) {
        this.database = database;
    }

    public SignOutResponse execute(SignOutRequest request) {
        //TODO validator for request
        // TODO this dude has no cart
        User newUser = database.accessUserDatabase().save(new User("Guest", "", "", UserRole.GUEST));
        request.getUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
