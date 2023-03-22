package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.services.actions.shared.SignInService;
import core.services.exception.InvalidLoginNameException;
import core.services.exception.InvalidLoginPasswordException;

public class SignInUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign in";
    private static final int ACCESS_NUM = UserRole.getAccessNumber(UserRole.ALLUSERS);

    private static final String PROMPT_TOPIC_NAME = "your login name: ";
    private static final String PROMPT_TOPIC_PASSWORD = "your password: ";
    private static final String MESSAGE_LOGIN = "Hello, ";

    private final SignInService signInService;
    private final UserCommunication userCommunication;

    public SignInUIAction(SignInService SignInService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUM);
        this.signInService = SignInService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String name = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PASSWORD);
        String password = userCommunication.getInput();
        try {
            signInService.execute(name, password);
            userCommunication.informUser(MESSAGE_LOGIN + name);
        } catch (InvalidLoginNameException | InvalidLoginPasswordException exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
