package core.services.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.responses.shared.SignInResponse;
import core.services.validators.shared.SignInValidator;

import java.util.List;

public class SignInService {

    private final Database database;
    private final SignInValidator validator;

    public SignInService(Database database, SignInValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SignInResponse execute(SignInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignInResponse(errors);
        }
        User newUser = database.accessUserDatabase().findByLogin(request.getLoginName()).get();
        request.getUserId().setValue(newUser.getId());
        return new SignInResponse(newUser);
    }

}
