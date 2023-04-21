package shop.console_ui.actions.manager;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.actions.manager.AddItemToShopService;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class AddItemToShopUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.MANAGER);

    private static final String PROMPT_TOPIC_ITEM = "item name to be added: ";
    private static final String PROMPT_TOPIC_PRICE = "item price: ";
    private static final String PROMPT_TOPIC_QUANTITY = "available quantity: ";
    private static final String MESSAGE_ITEM_ADDED = "New item added to the shop.";

    @DIDependency
    private AddItemToShopService addItemToShopService;
    @DIDependency
    private UserCommunication userCommunication;

    public AddItemToShopUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        String itemName = userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String price = userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String availableQuantity = userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
        AddItemToShopRequest request = new AddItemToShopRequest(itemName, price, availableQuantity);
        AddItemToShopResponse response = addItemToShopService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_ITEM_ADDED);

        }
    }

}
