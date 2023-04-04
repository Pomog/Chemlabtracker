package core.services.actions.shared;

import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.responses.shared.SignInResponse;
import core.services.validators.actions.shared.SignInValidator;
import core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

public class SignInService {

    private final SignInValidator validator;
    private final DatabaseAccessValidator databaseAccessValidator;

    public SignInService(SignInValidator validator, DatabaseAccessValidator databaseAccessValidator) {
        this.validator = validator;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public SignInResponse execute(SignInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignInResponse(errors);
        }
        User newUser = databaseAccessValidator.getUserByLoginName(request.getLoginName());
        request.getUserId().setValue(newUser.getId());
        return new SignInResponse(newUser);
    }

}
