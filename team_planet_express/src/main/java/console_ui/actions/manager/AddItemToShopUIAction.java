package console_ui.actions.manager;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.manager.AddItemToShopService;
import services.exception.InvalidInputException;
import services.exception.ItemAlreadyExistsException;

public class AddItemToShopUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.MANAGER);

    private static final String PROMPT_TOPIC_ITEM = "item name to be added: ";
    private static final String PROMPT_TOPIC_PRICE = "item price: ";
    private static final String PROMPT_TOPIC_QUANTITY = "available quantity: ";
    private static final String MESSAGE_ITEM_ADDED = "New item added to the shop.";

    private final AddItemToShopService addItemToShopService;
    private final UserCommunication userCommunication;

    public AddItemToShopUIAction(AddItemToShopService addItemToShopService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.addItemToShopService = addItemToShopService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String price = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
        String availableQuantity = userCommunication.getInput();
        try {
            addItemToShopService.execute(itemName, price, availableQuantity);
            userCommunication.informUser(MESSAGE_ITEM_ADDED);
        } catch (InvalidInputException /* TODO TEST BigDec !!! */ | ItemAlreadyExistsException exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
