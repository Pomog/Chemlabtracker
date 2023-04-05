package core.services.validators.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.requests.customer.BuyRequest;
import core.responses.CoreError;
import core.services.validators.cart.CartValidator;
import core.services.validators.universal.system.DatabaseAccessValidator;
import core.services.validators.universal.system.MutableLongUserIdValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;
    private final MutableLongUserIdValidator userIdValidator;
    private final CartValidator cartValidator;
    private final DatabaseAccessValidator databaseAccessValidator;

    public BuyValidator(Database database, MutableLongUserIdValidator userIdValidator, CartValidator cartValidator, DatabaseAccessValidator databaseAccessValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public List<CoreError> validate(BuyRequest request) {
        userIdValidator.validateMutableLongUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateCartIsNotEmpty(request.getUserId().getValue()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateCartIsNotEmpty(Long userId) {
        Cart cart = databaseAccessValidator.getOpenCartByUserId(userId);
        return (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0)
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

}
