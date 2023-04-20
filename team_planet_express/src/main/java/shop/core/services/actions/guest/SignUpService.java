package shop.core.services.actions.guest;

import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.user.UserRecord;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.guest.SignUpValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class SignUpService {

    @DIDependency
    private SignUpValidator validator;
    @DIDependency
    private UserService userService;

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
