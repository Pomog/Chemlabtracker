package core.services.validators.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.responses.customer.CoreError;
import core.services.cart.CartValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;

    public BuyValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(Long userId) {
        List<CoreError> errors = new ArrayList<>();
        validateCartIsNotEmpty(userId).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateCartIsNotEmpty(Long userId) {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), userId);

        return (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0)
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }
}
