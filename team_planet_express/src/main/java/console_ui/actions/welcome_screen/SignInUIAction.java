package console_ui.actions.welcome_screen;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.welcome_screen.SignInService;
import services.exception.WrongLoginName;
import services.exception.WrongLoginPassword;

public class SignInUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign in";
    private static final int ACCESS_NUM = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String PROMPT_TOPIC_NAME = "your login name ";
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
        } catch (WrongLoginName | WrongLoginPassword exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

    //TODO get user with customer role
    //TODO go to shop

}
