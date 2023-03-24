package core.services.validators.manager;

import core.database.Database;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddItemToShopValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String ERROR_NAME_MISSING = "Error: Item name is required.";
    private static final String ERROR_ITEM_EXISTS = "Error: Item with this name already exists.";
    private static final String ERROR_PRICE_MISSING = "Error: Price is required.";
    private static final String ERROR_PRICE_NOT_NUMBER = "Error: Price should be a number.";
    private static final String ERROR_PRICE_NEGATIVE = "Error: Price should not be negative.";
    private static final String ERROR_QUANTITY_MISSING = "Error: Quantity is required.";
    private static final String ERROR_QUANTITY_NOT_NUMBER = "Error: Quantity should be a number.";
    private static final String ERROR_QUANTITY_NEGATIVE = "Error: Quantity should not be negative.";
    private static final String ERROR_QUANTITY_DECIMAL = "Error: Quantity should not be decimal.";

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE_NUMBER = "[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_DECIMAL_NUMBER = "[0-9]+";

    private final Database database;
    private List<CoreError> errors;

    public AddItemToShopValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(AddItemToShopRequest request) {
        errors = new ArrayList<>();
        validateItemName(request.getItemName());
        validatePrice(request.getPrice());
        validateQuantity(request.getAvailableQuantity());
        return errors;
    }

    private void validateItemName(String itemName) {
        validateIsPresent(itemName, FIELD_NAME, ERROR_NAME_MISSING).ifPresent(errors::add);
        validateItemNameDoesNotAlreadyExist(itemName).ifPresent(errors::add);
    }

    private void validatePrice(String price) {
        validateIsPresent(price, FIELD_PRICE, ERROR_PRICE_MISSING).ifPresent(errors::add);
        validateIsNumber(price, FIELD_PRICE, ERROR_PRICE_NOT_NUMBER).ifPresent(errors::add);
        validateIsPositive(price, FIELD_PRICE, ERROR_PRICE_NEGATIVE).ifPresent(errors::add);
    }

    private void validateQuantity(String availableQuantity) {
        validateIsPresent(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_MISSING).ifPresent(errors::add);
        validateIsNumber(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_NOT_NUMBER).ifPresent(errors::add);
        validateIsPositive(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_NEGATIVE).ifPresent(errors::add);
        validateIsNotDecimal(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_DECIMAL).ifPresent(errors::add);
    }

    private Optional<CoreError> validateIsPresent(String value, String field, String errorMessage) {
        return (value == null || value.isBlank())
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameDoesNotAlreadyExist(String itemName) {
        return (itemName != null &&
                database.accessItemDatabase().findByName(itemName).isPresent())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsNumber(String value, String field, String errorMessage) {
        return (value != null &&
                !value.matches(REGEX_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsPositive(String value, String field, String errorMessage) {
        return (value != null &&
                !value.matches(REGEX_NOT_NEGATIVE_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsNotDecimal(String value, String field, String errorMessage) {
        return (value != null &&
                !value.matches(REGEX_NOT_DECIMAL_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

}
