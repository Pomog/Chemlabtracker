package shop.core.services.validators.actions.shared;

import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;

import java.util.ArrayList;
import java.util.List;

public class SignOutValidator {

    private final CurrentUserIdValidator userIdValidator;

    public SignOutValidator(CurrentUserIdValidator userIdValidator) {
        this.userIdValidator = userIdValidator;
    }

    public List<CoreError> validate(SignOutRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
