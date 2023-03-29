package console_ui.actions.customer;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.customer.ListCartItemsRequest;
import core.responses.customer.ListCartItemsResponse;
import core.services.actions.customer.ListCartItemsService;

import java.math.BigDecimal;

public class ListCartItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List cart items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String HEADER_TEXT = "Cart items:";
    private static final String MESSAGE_CART_IS_EMPTY = "Your cart is empty.";
    private static final String MESSAGE_CART_TOTAL = "Your cart total is: ";

    private final ListCartItemsService listCartItemsService;
    private final UserCommunication userCommunication;

    public ListCartItemsUIAction(ListCartItemsService listCartItemsService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.listCartItemsService = listCartItemsService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        ListCartItemsRequest request = new ListCartItemsRequest(listCartItemsService.getCurrentUserId().getValue());
        ListCartItemsResponse response = listCartItemsService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            printCartItems(response);
        }
    }

    private void printCartItems(ListCartItemsResponse response) {
        if (response.getCartItems().isEmpty()) {
            userCommunication.informUser(MESSAGE_CART_IS_EMPTY);
        } else {
            response.getCartItems().forEach(item -> userCommunication.informUser(item.toString()));
            BigDecimal cartTotal = response.getCartTotal();
            userCommunication.informUser(MESSAGE_CART_TOTAL + cartTotal);
        }
    }

}
