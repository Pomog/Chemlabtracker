package console_ui;

import database.CartItemDatabase;
import user_input.UserCommunication;

public class ListCartItemsUIAction implements UIAction {

    private static final String ACTION_NAME = "List cart items";

    private static final String HEADER_TEXT = "Cart items:";
    private static final String MESSAGE_CART_IS_EMPTY = "Your cart is empty.";

    private final CartItemDatabase cartItemDatabase;
    private final UserCommunication userCommunication;

    public ListCartItemsUIAction(CartItemDatabase cartItemDatabase, UserCommunication userCommunication) {
        this.cartItemDatabase = cartItemDatabase;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        if (cartItemDatabase.getAllCartItems().isEmpty()) {
            userCommunication.informUser(MESSAGE_CART_IS_EMPTY);
        } else {
            cartItemDatabase.getAllCartItems().forEach(cartItem -> userCommunication.informUser(cartItem.toString()));
        }
    }

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

}
