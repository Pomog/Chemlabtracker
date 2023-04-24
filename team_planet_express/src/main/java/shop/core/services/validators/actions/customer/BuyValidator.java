package shop.core.services.validators.actions.customer;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    @DIDependency
    private Database database;
    @DIDependency
    private CurrentUserIdValidator userIdValidator;
    @DIDependency
    private CartValidator cartValidator;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;


    public List<CoreError> validate(BuyRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
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
