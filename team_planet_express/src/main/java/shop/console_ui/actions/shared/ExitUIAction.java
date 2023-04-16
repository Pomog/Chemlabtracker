package shop.console_ui.actions.shared;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.services.actions.shared.ExitService;

public class ExitUIAction extends UIAction {

    private static final String ACTION_NAME = "Exit";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALL_USERS);

    private static final String MESSAGE_EXIT = "Thank you for shopping at Planet Express.";

    private final ExitService exitService;
    private final UserCommunication userCommunication;

    public ExitUIAction(ExitService exitService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.exitService = exitService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_EXIT);
        exitService.execute();
    }

}
