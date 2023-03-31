package core.services.validators.actions.shared;

import core.requests.shared.SignOutRequest;
import core.services.validators.universal.system.MutableLongUserIdValidator;

public class SignOutValidator {

    private final MutableLongUserIdValidator userIdValidator;

    public SignOutValidator(MutableLongUserIdValidator userIdValidator) {
        this.userIdValidator = userIdValidator;
    }

    public void validate(SignOutRequest request) {
        userIdValidator.validateMutableLongUserIdIsPresent(request.getUserId());
    }

}
