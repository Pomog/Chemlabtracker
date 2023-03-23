package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart.CartStatus;
import core.services.cart.CartValidator;
import core.services.exception.CartIsEmptyException;
import core.support.MutableLong;

import java.time.LocalDate;

public class BuyService {

    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;
    private final MutableLong currentUserId;

    public BuyService(Database database, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
    }

    public void execute() {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), currentUserId.getValue());
        if (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0) {
            throw new CartIsEmptyException(ERROR_CART_EMPTY);
        }
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
    }

}
