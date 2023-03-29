package console_ui.actions.guest;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.guest.SignUpRequest;
import core.responses.guest.SignUpResponse;
import core.services.actions.guest.SignUpService;
import core.support.MutableLong;

public class SignUpUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign up";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String PROMPT_TOPIC_NAME = "your name: ";
    private static final String PROMPT_TOPIC_LOGIN = "your login name: ";
    private static final String PROMPT_TOPIC_PASSWORD = "your password: ";
    private static final String MESSAGE_USER_CREATED = "Welcome to the Planet Express crew, ";
    private static final String MESSAGE_EXCLAMATION = "!";

    private final SignUpService signUpService;
    private final UserCommunication userCommunication;
    private final MutableLong currentUserId;

    public SignUpUIAction(SignUpService signUpService, MutableLong currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.signUpService = signUpService;
        this.userCommunication = userCommunication;
        this.currentUserId = currentUserId;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String name = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_LOGIN);
        String login = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PASSWORD);
        String password = userCommunication.getInput();
        SignUpRequest request = new SignUpRequest(name, login, password, currentUserId);
        SignUpResponse response = signUpService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            //TODO there must be a better way..
            userCommunication.informUser(MESSAGE_USER_CREATED + response.getUser().getName() + MESSAGE_EXCLAMATION);

        }
    }

}
