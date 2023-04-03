package console_ui.actions.admin;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.services.actions.admin.ChangeUserDataService;

public class ChangeUserDataUIAction extends UIAction {

    private static final String ACTION_NAME = "Change existing user data";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ADMIN);

    private static final String MESSAGE_SUCCESS = "User data changed.";

    private final ChangeUserDataService changeUserDataService;
    private final UserCommunication userCommunication;

    public ChangeUserDataUIAction(ChangeUserDataService changeUserDataService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.changeUserDataService = changeUserDataService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser("User transforming into an octopus.");
        userCommunication.informUser(MESSAGE_SUCCESS);
        //TODO change user
    }

}
