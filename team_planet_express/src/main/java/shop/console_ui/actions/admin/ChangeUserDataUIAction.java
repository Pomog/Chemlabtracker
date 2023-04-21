package shop.console_ui.actions.admin;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.services.actions.admin.ChangeUserDataService;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class ChangeUserDataUIAction extends UIAction {

    private static final String ACTION_NAME = "Change existing user data";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ADMIN);

    private static final String MESSAGE_SUCCESS = "User data changed.";
    @DIDependency
    private ChangeUserDataService changeUserDataService;
    @DIDependency
    private UserCommunication userCommunication;

    public ChangeUserDataUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        userCommunication.informUser("User transforming into an octopus.");
        userCommunication.informUser(MESSAGE_SUCCESS);
        //TODO change user
    }

}
