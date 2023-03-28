package core.services.validators.customer;

import core.database.Database;
import core.requests.customer.AddItemToCartRequest;
import core.responses.CoreError;
import core.services.cart.CartValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddItemToCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String ERROR_NO_SUCH_ITEM = "Error: No such item.";
    private static final String ERROR_QUANTITY_NOT_NUMBER = "Error: Quantity should be a number.";
    private static final String ERROR_QUANTITY_NOT_POSITIVE = "Error: Ordered amount should be more than zero.";
    private static final String ERROR_NOT_ENOUGH_QUANTITY = "Error: Available quantity lower than ordered amount.";

    private final Database database;
    private final CartValidator cartValidator;

    public AddItemToCartValidator(Database database, CartValidator cartValidator) {
        this.database = database;
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(AddItemToCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemNameExistsInShop(request).ifPresent(errors::add);
            validateOrderedQuantityIsNumber(request).ifPresent(errors::add);
            if (errors.isEmpty()) {
                validateOrderedQuantityGreaterThanZero(request).ifPresent(errors::add);
                validateOrderedQuantityNotGreaterThanAvailable(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validateItemNameExistsInShop(AddItemToCartRequest request) {
        return (request.getItemName() == null ||
                database.accessItemDatabase().findByName(request.getItemName()).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderedQuantityIsNumber(AddItemToCartRequest request) {
        return (request.getOrderedQuantity() == null ||
                !request.getOrderedQuantity().strip().chars().allMatch(Character::isDigit))
                ? Optional.of(new CoreError(FIELD_QUANTITY, ERROR_QUANTITY_NOT_NUMBER))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderedQuantityGreaterThanZero(AddItemToCartRequest request) {
        return (Integer.parseInt(request.getOrderedQuantity()) <= 0)
                ? Optional.of(new CoreError(FIELD_QUANTITY, ERROR_QUANTITY_NOT_POSITIVE))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderedQuantityNotGreaterThanAvailable(AddItemToCartRequest request) {
        return (Integer.parseInt(request.getOrderedQuantity()) >
                database.accessItemDatabase().findByName(request.getItemName()).get().getAvailableQuantity())
                ? Optional.of(new CoreError(FIELD_QUANTITY, ERROR_NOT_ENOUGH_QUANTITY))
                : Optional.empty();
    }

}
