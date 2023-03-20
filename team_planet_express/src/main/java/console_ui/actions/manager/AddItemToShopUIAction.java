package console_ui.actions.manager;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.manager.AddItemToShopService;

public class AddItemToShopUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.MANAGER);

    private static final String MESSAGE_SUCCESS = "Item added.";

    private final AddItemToShopService addItemToShopService;
    private final UserCommunication userCommunication;

    public AddItemToShopUIAction(AddItemToShopService addItemToShopService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.addItemToShopService = addItemToShopService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser("Item adding in progress."); //TODO add item
        userCommunication.informUser(MESSAGE_SUCCESS);
    }

}
