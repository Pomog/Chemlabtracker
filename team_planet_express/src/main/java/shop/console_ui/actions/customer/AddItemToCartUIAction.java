package shop.console_ui.actions.customer;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.support.CurrentUserId;

public class AddItemToCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to order: ";
    private static final String PROMPT_TOPIC_QUANTITY = "quantity to be ordered: ";
    private static final String MESSAGE_ITEM_ADDED = "Item added to your cart.";

    private final AddItemToCartService addItemToCartService;
    private final CurrentUserId currentUserId;
    private final UserCommunication userCommunication;

    public AddItemToCartUIAction(AddItemToCartService addItemToCartService, CurrentUserId currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.addItemToCartService = addItemToCartService;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
        String orderedQuantity = userCommunication.getInput();
        AddItemToCartRequest request =
                new AddItemToCartRequest(currentUserId, itemName, orderedQuantity);
        AddItemToCartResponse response = addItemToCartService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_ITEM_ADDED);
        }
    }

}
