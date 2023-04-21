package shop.console_ui.actions.customer;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.support.CurrentUserId;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class AddItemToCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Add item to the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to order: ";
    private static final String PROMPT_TOPIC_QUANTITY = "quantity to be ordered: ";
    private static final String MESSAGE_ITEM_ADDED = "Item added to your cart.";

    @DIDependency
    private AddItemToCartService addItemToCartService;
    @DIDependency
    private CurrentUserId currentUserId;
    @DIDependency
    private UserCommunication userCommunication;

    public AddItemToCartUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        String itemName = userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String orderedQuantity = userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
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
