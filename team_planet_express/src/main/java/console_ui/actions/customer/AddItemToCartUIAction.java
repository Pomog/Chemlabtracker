package console_ui.actions.customer;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.services.actions.customer.AddItemToCartService;
import core.services.exception.InvalidInputException;
import core.services.exception.InvalidQuantityException;
import core.services.exception.ItemNotFoundException;
import core.services.exception.NoOpenCartException;

public class AddItemToCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to order: ";
    private static final String PROMPT_TOPIC_QUANTITY = "quantity to be ordered: ";
    private static final String MESSAGE_ITEM_ADDED = "Item added to your cart.";

    private final AddItemToCartService addItemToCartService;
    private final UserCommunication userCommunication;

    public AddItemToCartUIAction(AddItemToCartService addItemToCartService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.addItemToCartService = addItemToCartService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
        String orderedQuantity = userCommunication.getInput();
        try {
            addItemToCartService.execute(itemName, orderedQuantity);
            userCommunication.informUser(MESSAGE_ITEM_ADDED);
        } catch (InvalidInputException | ItemNotFoundException |
                 InvalidQuantityException | NoOpenCartException exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
