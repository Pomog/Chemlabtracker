package core.services.actions.shared;

import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.shared.SignOutRequest;
import core.responses.shared.SignOutResponse;
import core.services.user.UserRecord;
import core.services.user.UserService;

public class SignOutService {

    public static final String BLANK = "";

    private final UserService userService;

    public SignOutService(UserService userService) {
        this.userService = userService;
    }

    public SignOutResponse execute(SignOutRequest request) {
        //TODO validator for request
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User newUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        request.getUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
