package console_ui.actions.common;

import console_ui.actions.UIAction;
import domain.user.UserRole;

//TODO probably yeetable
public class SubMenuExitUIAction extends UIAction {

    private static final String ACTION_NAME = "Back";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.CUSTOMER, UserRole.MANAGER, UserRole.ADMIN);

    public SubMenuExitUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        //TODO it literally does nothing
    }

}
