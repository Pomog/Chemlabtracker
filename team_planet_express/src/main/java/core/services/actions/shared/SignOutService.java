package core.services.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.shared.SignOutRequest;
import core.responses.shared.SignOutResponse;
import core.services.validators.actions.shared.SignOutValidator;

public class SignOutService {

    private static final User DEFAULT_USER = new User("Guest", "", "", UserRole.GUEST);

    private final Database database;
    private final SignOutValidator validator;

    public SignOutService(Database database, SignOutValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SignOutResponse execute(SignOutRequest request) {
        validator.validate(request);
        //TODO search, then create
        //TODO this dude has no cart
        User newUser = database.accessUserDatabase().save(DEFAULT_USER);
        request.getUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
