package console_ui.actions.welcome_screen;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.welcome_screen.ShopEntranceService;

public class ShopEntranceUIAction extends UIAction {

    private static final String ACTION_NAME = "Shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String MESSAGE_WELCOME = "Welcome to Planet Express!";

    private final ShopEntranceService shopEntranceService;
    private final UserCommunication userCommunication;

    public ShopEntranceUIAction(ShopEntranceService shopEntranceService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.shopEntranceService = shopEntranceService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_WELCOME);
        userCommunication.informUser("There is shop menu aka add, remove, list etc.");
        //TODO change guest to customer
    }

}
