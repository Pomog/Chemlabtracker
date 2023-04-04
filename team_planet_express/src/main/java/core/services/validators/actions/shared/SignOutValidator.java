package core.services.validators.actions.shared;

import core.requests.shared.SignOutRequest;
import core.responses.CoreError;
import core.services.validators.universal.system.MutableLongUserIdValidator;

import java.util.ArrayList;
import java.util.List;

public class SignOutValidator {

    private final MutableLongUserIdValidator userIdValidator;

    public SignOutValidator(MutableLongUserIdValidator userIdValidator) {
        this.userIdValidator = userIdValidator;
    }

    public List<CoreError> validate(SignOutRequest request) {
        userIdValidator.validateMutableLongUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
