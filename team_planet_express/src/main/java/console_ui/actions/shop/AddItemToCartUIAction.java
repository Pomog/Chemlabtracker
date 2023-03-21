package console_ui.actions.shop;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.shop.AddItemToCartService;
import services.exception.InvalidInputException;
import services.exception.InvalidQuantityException;
import services.exception.ItemNotFoundException;
import services.exception.NoOpenCartException;

public class AddItemToCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.CUSTOMER);

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
