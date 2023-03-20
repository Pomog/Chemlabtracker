package console_ui.actions;

import console_ui.UserCommunication;
import domain.cart_item.CartItem;
import domain.user.UserRole;
import services.actions.ListCartItemsService;
import services.exception.NoOpenCartException;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List cart items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALLUSERS);

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
        try {
            List<CartItem> cartItems = listCartItemsService.execute();
            if (cartItems.isEmpty()) {
                userCommunication.informUser(MESSAGE_CART_IS_EMPTY);
            } else {
                cartItems.forEach(item -> userCommunication.informUser(item.toString()));
                BigDecimal cartTotal = listCartItemsService.getCartTotal();
                userCommunication.informUser(MESSAGE_CART_TOTAL + cartTotal);
            }
        } catch (NoOpenCartException exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
