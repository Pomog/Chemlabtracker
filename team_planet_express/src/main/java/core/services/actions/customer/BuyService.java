package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart.CartStatus;
import core.domain.user.User;
import core.services.exception.CartIsEmptyException;
import core.services.cart.CartValidator;

import java.time.LocalDate;

public class BuyService {

    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;
    private final User user;

    public BuyService(Database database, User user) {
        this.database = database;
        this.user = user;
    }

    public void execute() {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), user.getId());
        if (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0) {
            throw new CartIsEmptyException(ERROR_CART_EMPTY);
        }
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
    }

}
