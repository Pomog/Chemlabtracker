package core.services.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.responses.shared.SignInResponse;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.actions.shared.SignInValidator;
import core.support.MutableLong;

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
        User newUser = getUserByLoginName(request.getLoginName());
        request.getUserId().setValue(newUser.getId());
        return new SignInResponse(newUser);
    }

    //TODO yeet, duplicate
    private User getUserByLoginName(String login) {
        return database.accessUserDatabase().findByLogin(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
