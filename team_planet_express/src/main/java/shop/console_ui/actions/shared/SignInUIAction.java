package shop.console_ui.actions.shared;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.shared.SignInResponse;
import shop.core.services.actions.shared.SignInService;
import shop.core.support.CurrentUserId;

public class SignInUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign in";
    private static final int ACCESS_NUM = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String PROMPT_TOPIC_LOGIN = "your login name: ";
    private static final String PROMPT_TOPIC_PASSWORD = "your password: ";
    private static final String MESSAGE_LOGIN = "Welcome back, ";
    private static final String MESSAGE_EXCLAMATION = "!";

    private final SignInService signInService;
    private final CurrentUserId currentUserId;
    private final UserCommunication userCommunication;

    public SignInUIAction(SignInService SignInService, CurrentUserId currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUM);
        this.signInService = SignInService;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        String login = userCommunication.requestInput(PROMPT_TOPIC_LOGIN);
        String password = userCommunication.requestInput(PROMPT_TOPIC_PASSWORD);
        SignInRequest request = new SignInRequest(currentUserId, login, password);
        SignInResponse response = signInService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            //TODO there must still be a better way of composing strings
            userCommunication.informUser(MESSAGE_LOGIN + response.getUser().getName() + MESSAGE_EXCLAMATION);
        }
    }

}
