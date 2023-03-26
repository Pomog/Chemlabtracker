package console_ui;

import cart.Cart;
import cart.CartStatus;
import database.CartDatabase;
import user_input.UserCommunication;

import java.time.LocalDate;
import java.util.Optional;

public class BuyUIAction implements UIAction {

    private static final String ACTION_NAME = "Buy items in the cart";

    private static final String MESSAGE_CART_IS_CLOSED = "Your cart is closed now.";
    private static final String ERROR_NO_OPEN_CART = "You do not have an open cart.";

    private final CartDatabase cartDatabase;
    private final UserCommunication userCommunication;

    public BuyUIAction(CartDatabase cartDatabase, UserCommunication userCommunication) {
        this.cartDatabase = cartDatabase;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        Optional<Cart> userCart = cartDatabase.getAllCarts().stream()
                .filter(cart -> cart.getCartStatus().equals(CartStatus.OPEN))
                .findFirst();
        if (userCart.isPresent()) {
            cartDatabase.changeCartStatus(userCart.get().getId(), CartStatus.CLOSED);
            cartDatabase.changeLastActionDate(userCart.get().getId(), LocalDate.now());
            userCommunication.informUser(MESSAGE_CART_IS_CLOSED);
        } else {
            userCommunication.informUser(ERROR_NO_OPEN_CART);
        }
    }

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

}
