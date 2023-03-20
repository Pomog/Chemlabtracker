package console_ui.actions;

import console_ui.UserCommunication;
import domain.user.UserRole;
import services.actions.LoginService;
import services.exception.WrongLoginName;
import services.exception.WrongLoginPassword;

public class LoginUIAction extends UIAction {

    private static final String ACTION_NAME = "Log into the system";
    private static final int ACCESS_NUM = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String PROMPT_TOPIC_NAME = "your login name ";
    private static final String PROMPT_TOPIC_PASSWORD = "your password: ";
    private static final String MESSAGE_LOGIN = "Hello, ";

    private final LoginService LoginService;
    private final UserCommunication userCommunication;

    public LoginUIAction(LoginService LoginService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUM);
        this.LoginService = LoginService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String name = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PASSWORD);
        String password = userCommunication.getInput();
        try {
            LoginService.execute(name, password);
            userCommunication.informUser(MESSAGE_LOGIN + name);
        } catch (WrongLoginName | WrongLoginPassword exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
