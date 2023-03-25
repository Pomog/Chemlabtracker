package core.services.cart;

import core.database.Database;
import core.responses.CoreError;

import java.util.Optional;

public class CartValidator {

    private static final String FIELD_BUTTON = "button";
    private static final String ERROR_NO_OPEN_CART = "You do not have an open cart.";

    private final Database database;

    public CartValidator(Database database) {
        this.database = database;
    }

    public Optional<CoreError> validateOpenCartExistsForUserId(Long userId) {
        return (database.accessCartDatabase().findOpenCartForUserId(userId).isEmpty())
                ? Optional.of(new CoreError(FIELD_BUTTON, ERROR_NO_OPEN_CART))
                : Optional.empty();
    }

}
