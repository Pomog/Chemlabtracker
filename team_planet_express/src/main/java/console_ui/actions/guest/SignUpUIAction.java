package console_ui.actions.guest;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.services.actions.guest.SignUpService;

public class SignUpUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign up";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String MESSAGE_SUCCESS = "Welcome to the Planet Express crew!";

    private final SignUpService signUpService;
    private final UserCommunication userCommunication;

    public SignUpUIAction(SignUpService signUpService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.signUpService = signUpService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser("Sign up process go brrrrr."); //TODO sign up a dude
        userCommunication.informUser(MESSAGE_SUCCESS);
        //TODO change guest to customer
        //TODO go to shop
    }

}
