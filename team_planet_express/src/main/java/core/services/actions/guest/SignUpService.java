package core.services.actions.guest;

import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.responses.guest.SignUpResponse;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.services.validators.guest.SignUpValidator;

import java.util.List;

public class SignUpService {

    private final SignUpValidator validator;
    private final UserService userService;

    public SignUpService(SignUpValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    public SignUpResponse execute(SignUpRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignUpResponse(errors);
        }
        String name = request.getName();
        String loginName = request.getLoginName();
        String password = request.getPassword();
        UserRecord userRecord = new UserRecord(name, loginName, password, UserRole.CUSTOMER);
        User createdUser = userService.createUser(userRecord);
        request.getUserId().setValue(createdUser.getId());
        return new SignUpResponse(createdUser);
    }

}
