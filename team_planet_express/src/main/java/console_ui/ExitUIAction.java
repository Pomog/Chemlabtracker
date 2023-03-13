package console_ui;

import user_input.UserCommunication;

public class ExitUIAction implements UIAction {

    private static final String ACTION_NAME = "Exit";

    private static final String MESSAGE_EXIT = "Thank you for shopping at Planet Express.";

    private final UserCommunication userCommunication;

    public ExitUIAction(UserCommunication userCommunication) {
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_EXIT);
        System.exit(0);
    }

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

}
