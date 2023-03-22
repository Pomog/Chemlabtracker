package core.services.cart;

import core.database.CartDatabase;
import core.domain.cart.Cart;
import core.services.exception.NoOpenCartException;

import java.util.Optional;

public class CartValidator {

    private static final String ERROR_NO_OPEN_CART = "You do not have an open cart.";

    public Cart getOpenCartForUserId(CartDatabase cartDatabase, Long userId) {
        Optional<Cart> cart = cartDatabase.findOpenCartForUserId(userId);
        if (cart.isEmpty()) {
            throw new NoOpenCartException(ERROR_NO_OPEN_CART);
        } else {
            return cart.get();
        }
    }

}
