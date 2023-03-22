package console_ui.actions.manager;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.services.actions.manager.ChangeItemDataService;

public class ChangeItemDataUIAction extends UIAction {

    private static final String ACTION_NAME = "Change existing item data";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.MANAGER);

    private static final String MESSAGE_SUCCESS = "Item changed.";

    private final ChangeItemDataService changeItemDataService;
    private final UserCommunication userCommunication;

    public ChangeItemDataUIAction(ChangeItemDataService changeItemDataService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.changeItemDataService = changeItemDataService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser("Item changing.");
        userCommunication.informUser(MESSAGE_SUCCESS);
        //TODO change an item
    }

}
