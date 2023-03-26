package core.services.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.shared.SignOutRequest;
import core.responses.shared.SignOutResponse;
import core.support.MutableLong;

public class SignOutService {
    private final Database database;
    private final MutableLong currentUserId;

    public SignOutService(Database database, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
    }

    public SignOutResponse execute(SignOutRequest request) {
        User newUser = database.accessUserDatabase().save(new User("Guest", "", "", UserRole.GUEST));
        currentUserId.setValue(newUser.getId());
        return new SignOutResponse();
    }
}
