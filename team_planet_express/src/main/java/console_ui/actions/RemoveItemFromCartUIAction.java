package console_ui.actions;

import console_ui.UserCommunication;
import domain.user.UserRole;
import services.actions.RemoveItemFromCartService;
import services.exception.ItemNotFoundException;
import services.exception.NoOpenCartException;

public class RemoveItemFromCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Remove item from the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALLUSERS);

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to remove: ";
    private static final String MESSAGE_ITEM_REMOVED = "Item removed from your cart.";

    private final RemoveItemFromCartService removeItemFromCartService;
    private final UserCommunication userCommunication;

    public RemoveItemFromCartUIAction(RemoveItemFromCartService removeItemFromCartService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.removeItemFromCartService = removeItemFromCartService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String userInputItem = userCommunication.getInput();
        try {
            removeItemFromCartService.execute(userInputItem);
            userCommunication.informUser(MESSAGE_ITEM_REMOVED);
        } catch (ItemNotFoundException | NoOpenCartException exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
