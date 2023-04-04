package core.services.actions.shared;

import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.shared.SignOutRequest;
import core.responses.CoreError;
import core.responses.shared.SignOutResponse;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.services.validators.actions.shared.SignOutValidator;

import java.util.List;

public class SignOutService {

    public static final String BLANK = "";

    private final SignOutValidator validator;
    private final UserService userService;

    public SignOutService(SignOutValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

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
