package core.services.validators.actions.manager;

import core.database.Database;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.services.validators.universal.user_input.NumberValidator;
import core.services.validators.universal.user_input.PresenceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddItemToShopValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String VALUE_NAME_PRICE = "Price";
    private static final String VALUE_NAME_QUANTITY = "Quantity";
    private static final String ERROR_ITEM_EXISTS = "Error: Item with this name already exists.";

    private final Database database;
    private final PresenceValidator presenceValidator;
    private final NumberValidator numberValidator;
    private List<CoreError> errors;

    public AddItemToShopValidator(Database database, PresenceValidator presenceValidator, NumberValidator numberValidator) {
        this.database = database;
        this.presenceValidator = presenceValidator;
        this.numberValidator = numberValidator;
    }

    public List<CoreError> validate(AddItemToShopRequest request) {
        errors = new ArrayList<>();
        validateItemName(request.getItemName());
        validatePrice(request.getPrice());
        validateQuantity(request.getAvailableQuantity());
        return errors;
    }

    private void validateItemName(String itemName) {
        presenceValidator.validateStringIsPresent(itemName, FIELD_NAME, VALUE_NAME_ITEM).ifPresent(errors::add);
        validateItemNameDoesNotAlreadyExist(itemName).ifPresent(errors::add);
    }

    private void validatePrice(String price) {
        presenceValidator.validateStringIsPresent(price, FIELD_PRICE, VALUE_NAME_PRICE).ifPresent(errors::add);
        numberValidator.validateIsNumber(price, FIELD_PRICE, VALUE_NAME_PRICE).ifPresent(errors::add);
        numberValidator.validateIsNotNegative(price, FIELD_PRICE, VALUE_NAME_PRICE).ifPresent(errors::add);
    }

    private void validateQuantity(String availableQuantity) {
        presenceValidator.validateStringIsPresent(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
        numberValidator.validateIsNumber(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
        numberValidator.validateIsNotNegative(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
        numberValidator.validateIsNotDecimal(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameDoesNotAlreadyExist(String itemName) {
        return (itemName != null && !itemName.isBlank() &&
                database.accessItemDatabase().findByName(itemName).isPresent())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

}
