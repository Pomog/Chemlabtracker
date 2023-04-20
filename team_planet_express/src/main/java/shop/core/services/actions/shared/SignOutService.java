package shop.core.services.actions.shared;

import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.user.UserRecord;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.shared.SignOutValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class SignOutService {

    public static final String BLANK = "";

    @DIDependency
    private SignOutValidator validator;
    @DIDependency
    private UserService userService;

    public SignOutResponse execute(SignOutRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignOutResponse(errors);
        }
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User newUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        request.getUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
