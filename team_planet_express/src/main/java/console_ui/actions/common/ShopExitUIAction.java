package console_ui.actions.common;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.common.ShopExitService;

public class ShopExitUIAction extends UIAction {

    private static final String ACTION_NAME = "Exit";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALLUSERS);

    private static final String MESSAGE_EXIT = "Thank you for shopping at Planet Express.";

    private final ShopExitService shopExitService;
    private final UserCommunication userCommunication;

    public ShopExitUIAction(ShopExitService shopExitService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.shopExitService = shopExitService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_EXIT);
        shopExitService.execute();
    }

}
