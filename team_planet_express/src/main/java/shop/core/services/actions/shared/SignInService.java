package shop.core.services.actions.shared;

import shop.core.domain.user.User;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignInResponse;
import shop.core.services.validators.actions.shared.SignInValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

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
