package shop.core.services.validators.actions.manager;

import shop.core.database.Database;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

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

    public AddItemToShopValidator(Database database, InputStringValidator inputStringValidator) {
        this.database = database;
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(AddItemToShopRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateItemName(request.getItemName(), errors);
        validatePrice(request.getPrice(), errors);
        validateQuantity(request.getAvailableQuantity(), errors);
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, FIELD_NAME, VALUE_NAME_ITEM);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateItemNameDoesNotAlreadyExist(itemName).ifPresent(errors::add);
    }

    private void validatePrice(String price, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        errors.addAll(inputStringValidator.validateIsNumberNotNegative(inputStringValidatorData));
    }

    private void validateQuantity(String availableQuantity, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        errors.addAll(inputStringValidator.validateIsNumberNotNegativeNotDecimal(inputStringValidatorData));
    }

    private Optional<CoreError> validateItemNameDoesNotAlreadyExist(String itemName) {
        return (itemName != null && !itemName.isBlank() &&
                database.accessItemDatabase().findByName(itemName).isPresent())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

}
