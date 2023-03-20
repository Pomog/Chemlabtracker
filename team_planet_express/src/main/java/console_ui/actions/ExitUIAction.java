package console_ui.actions;

import console_ui.UserCommunication;
import domain.user.UserRole;
import services.actions.ExitService;

public class ExitUIAction extends UIAction {

    private static final String ACTION_NAME = "Exit";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALLUSERS);

    private static final String MESSAGE_EXIT = "Thank you for shopping at Planet Express.";

    private final UserCommunication userCommunication;
    private final ExitService exitService;

    public ExitUIAction(ExitService exitService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.userCommunication = userCommunication;
        this.exitService = exitService;
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_EXIT);
        exitService.execute();
    }

}
