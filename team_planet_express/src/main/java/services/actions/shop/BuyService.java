package services.actions.shop;

import database.Database;
import domain.cart.Cart;
import domain.cart.CartStatus;
import domain.user.User;
import services.exception.CartIsEmptyException;
import validator.CartValidator;

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
