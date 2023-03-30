package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.shared.SignInRequest;
import core.responses.shared.SignInResponse;
import core.services.actions.shared.SignInService;
import core.support.MutableLong;

public class SignInUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign in";
    private static final int ACCESS_NUM = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String PROMPT_TOPIC_LOGIN = "your login name: ";
    private static final String PROMPT_TOPIC_PASSWORD = "your password: ";
    private static final String MESSAGE_LOGIN = "Welcome back, ";
    private static final String MESSAGE_EXCLAMATION = "!";

    private final SignInService signInService;
    private final MutableLong currentUserId;
    private final UserCommunication userCommunication;

    public SignInUIAction(SignInService SignInService, MutableLong currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUM);
        this.signInService = SignInService;
        this.userCommunication = userCommunication;
        this.currentUserId = currentUserId;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_LOGIN);
        String login = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PASSWORD);
        String password = userCommunication.getInput();
        SignInRequest request = new SignInRequest(currentUserId, login, password);
        SignInResponse response = signInService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            //TODO there must still be a better way..
            userCommunication.informUser(MESSAGE_LOGIN + response.getUser().getName() + MESSAGE_EXCLAMATION);
        }
    }

}
