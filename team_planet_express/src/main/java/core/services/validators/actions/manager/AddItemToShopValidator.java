package core.services.validators.actions.manager;

import core.database.Database;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;

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
    private final InputStringValidator inputStringValidator;
    private List<CoreError> errors;

    public AddItemToShopValidator(Database database, InputStringValidator inputStringValidator) {
        this.database = database;
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(AddItemToShopRequest request) {
        errors = new ArrayList<>();
        validateItemName(request.getItemName());
        validatePrice(request.getPrice());
        validateQuantity(request.getAvailableQuantity());
        return errors;
    }

    private void validateItemName(String itemName) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(itemName, FIELD_NAME, VALUE_NAME_ITEM);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        validateItemNameDoesNotAlreadyExist(itemName).ifPresent(errors::add);
    }

    private void validatePrice(String price) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

    private void validateQuantity(String availableQuantity) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotDecimal(record).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameDoesNotAlreadyExist(String itemName) {
        return (itemName != null && !itemName.isBlank() &&
                database.accessItemDatabase().findByName(itemName).isPresent())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

}
