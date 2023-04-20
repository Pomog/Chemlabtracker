package shop.core.services.validators.cart;

import shop.core.database.Database;
import shop.core.responses.CoreError;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.Optional;

@DIComponent
public class CartValidator {

    private static final String FIELD_BUTTON = "button";
    private static final String ERROR_NO_OPEN_CART = "You do not have an open cart.";

    @DIDependency
    private Database database;

    public Optional<CoreError> validateOpenCartExistsForUserId(Long userId) {
        return (database.accessCartDatabase().findOpenCartForUserId(userId).isEmpty())
                ? Optional.of(new CoreError(FIELD_BUTTON, ERROR_NO_OPEN_CART))
                : Optional.empty();
    }

}
