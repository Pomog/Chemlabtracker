package shop.core.services.validators.actions.shared;

import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SignOutValidator {

    @DIDependency
    private CurrentUserIdValidator userIdValidator;

    public List<CoreError> validate(SignOutRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
