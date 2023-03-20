package services.actions;

import database.Database;
import domain.cart.Cart;
import domain.cart.CartStatus;
import services.exception.CartIsEmptyException;
import validator.CartValidator;

import java.time.LocalDate;

public class BuyService {

    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;
    private final Long userId;

    public BuyService(Database database, Long userId) {
        this.database = database;
        this.userId = userId;
    }

    public void execute() {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), userId);
        if (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0) {
            throw new CartIsEmptyException(ERROR_CART_EMPTY);
        }
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
    }

}
