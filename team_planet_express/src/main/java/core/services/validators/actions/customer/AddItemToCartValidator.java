package core.services.validators.actions.customer;

import core.database.Database;
import core.domain.item.Item;
import core.requests.customer.AddItemToCartRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.cart.CartValidator;
import core.services.validators.universal.system.LongUserIdValidator;
import core.services.validators.universal.user_input.NumberValidator;
import core.services.validators.universal.user_input.PresenceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddItemToCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String VALUE_NAME_QUANTITY = "Quantity";
    private static final String ERROR_NO_SUCH_ITEM = "Error: No such item.";
    private static final String ERROR_NOT_ENOUGH_QUANTITY = "Error: Available quantity lower than ordered amount.";

    private final Database database;
    private final LongUserIdValidator userIdValidator;
    private final CartValidator cartValidator;
    private final PresenceValidator presenceValidator;
    private final NumberValidator numberValidator;
    private List<CoreError> errors;

    public AddItemToCartValidator(Database database, LongUserIdValidator userIdValidator, CartValidator cartValidator, PresenceValidator presenceValidator, NumberValidator numberValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
        this.presenceValidator = presenceValidator;
        this.numberValidator = numberValidator;
    }

    public List<CoreError> validate(AddItemToCartRequest request) {
        userIdValidator.validateLongUserIdIsPresent(request.getUserId());
        errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemName(request.getItemName());
            validateQuantity(request.getOrderedQuantity());
            if (errors.isEmpty()) {
                validateOrderedQuantityNotGreaterThanAvailable(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private void validateItemName(String itemName) {
        presenceValidator.validateStringIsPresent(itemName, FIELD_NAME, VALUE_NAME_ITEM).ifPresent(errors::add);
        validateItemNameExistsInShop(itemName).ifPresent(errors::add);
    }

    private void validateQuantity(String orderedQuantity) {
        presenceValidator.validateStringIsPresent(orderedQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
        numberValidator.validateIsNumber(orderedQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
        numberValidator.validateIsGreaterThanZero(orderedQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
        numberValidator.validateIsNotDecimal(orderedQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameExistsInShop(String itemName) {
        return (itemName == null ||
                database.accessItemDatabase().findByName(itemName).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderedQuantityNotGreaterThanAvailable(AddItemToCartRequest request) {
        return (Integer.parseInt(request.getOrderedQuantity()) >
                getItemByName(request.getItemName()).getAvailableQuantity())
                ? Optional.of(new CoreError(FIELD_QUANTITY, ERROR_NOT_ENOUGH_QUANTITY))
                : Optional.empty();
    }

    //TODO yeet, duplicate
    private Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
