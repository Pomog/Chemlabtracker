package core.services.validators.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.requests.customer.BuyRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.cart.CartValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;
    private final CartValidator cartValidator;

    public BuyValidator(Database database, CartValidator cartValidator) {
        this.database = database;
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(BuyRequest request) {
        List<CoreError> errors = new ArrayList<>();
        //TODO validate id
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateCartIsNotEmpty(request.getUserId()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateCartIsNotEmpty(Long userId) {
        Cart cart = getOpenCartForUserId(userId);
        return (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0)
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

    //TODO yeet, duplicate
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
