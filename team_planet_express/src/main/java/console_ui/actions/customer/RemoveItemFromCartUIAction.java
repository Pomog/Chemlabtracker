package console_ui.actions.customer;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.customer.RemoveItemFromCartResponse;
import core.services.actions.customer.RemoveItemFromCartService;
import core.support.CurrentUserId;

public class RemoveItemFromCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Remove item from the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to remove: ";
    private static final String MESSAGE_ITEM_REMOVED = "Item removed from your cart.";

    private final RemoveItemFromCartService removeItemFromCartService;
    private final CurrentUserId currentUserId;
    private final UserCommunication userCommunication;


    public RemoveItemFromCartUIAction(RemoveItemFromCartService removeItemFromCartService, CurrentUserId currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.removeItemFromCartService = removeItemFromCartService;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String itemName = userCommunication.getInput();
        RemoveItemFromCartRequest request =
                new RemoveItemFromCartRequest(currentUserId, itemName);
        RemoveItemFromCartResponse response = removeItemFromCartService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_ITEM_REMOVED);
        }
    }

}
