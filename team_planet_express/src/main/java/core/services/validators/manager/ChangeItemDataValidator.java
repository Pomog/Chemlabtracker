package core.services.validators.manager;

import core.database.Database;
import core.domain.item.Item;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChangeItemDataValidator {

    private static final String FIELD_ID = "id";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String FIELD_BUTTON = "button";
    private static final String ERROR_ID_MISSING = "Error: Item id is required.";
    private static final String ERROR_ID_NOT_NUMBER = "Error: Item id should be a number.";
    private static final String ERROR_ID_NEGATIVE = "Error: Item id should not be negative.";
    private static final String ERROR_ID_DECIMAL = "Error: Item id should not be decimal.";
    private static final String ERROR_ID_NOT_EXISTS = "Error: Item with this id does not exist.";
    private static final String ERROR_PRICE_NOT_NUMBER = "Error: Price should be a number.";
    private static final String ERROR_PRICE_NEGATIVE = "Error: Price should not be negative.";
    private static final String ERROR_QUANTITY_NOT_NUMBER = "Error: Quantity should be a number.";
    private static final String ERROR_QUANTITY_NEGATIVE = "Error: Quantity should not be negative.";
    private static final String ERROR_QUANTITY_DECIMAL = "Error: Quantity should not be decimal.";
    private static final String ERROR_ITEM_EXISTS = "Error: Exactly the same item already exists.";

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE_NUMBER = "[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_DECIMAL_NUMBER = "[0-9]+";

    private final Database database;
    private List<CoreError> errors;

    public ChangeItemDataValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(ChangeItemDataRequest request) {
        errors = new ArrayList<>();
        validateId(request.getItemId());
        validatePrice(request.getNewPrice());
        validateQuantity(request.getNewAvailableQuantity());
        if (errors.isEmpty()) {
            validateDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateId(String id) {
        validateIdIsPresent(id).ifPresent(errors::add);
        validateIsNumber(id, FIELD_ID, ERROR_ID_NOT_NUMBER).ifPresent(errors::add);
        validateIsPositive(id, FIELD_ID, ERROR_ID_NEGATIVE).ifPresent(errors::add);
        validateIsNotDecimal(id, FIELD_ID, ERROR_ID_DECIMAL).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateIdExistsInShop(id).ifPresent(errors::add);
        }
    }

    private void validatePrice(String price) {
        validateIsNumber(price, FIELD_PRICE, ERROR_PRICE_NOT_NUMBER).ifPresent(errors::add);
        validateIsPositive(price, FIELD_PRICE, ERROR_PRICE_NEGATIVE).ifPresent(errors::add);
    }

    private void validateQuantity(String availableQuantity) {
        validateIsNumber(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_NOT_NUMBER).ifPresent(errors::add);
        validateIsPositive(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_NEGATIVE).ifPresent(errors::add);
        validateIsNotDecimal(availableQuantity, FIELD_QUANTITY, ERROR_QUANTITY_DECIMAL).ifPresent(errors::add);
    }

    private Optional<CoreError> validateDuplicate(ChangeItemDataRequest request) {
        Item originalItem = getItemById(Long.parseLong(request.getItemId()));
        String newItemName = setNewItemName(request, originalItem);
        BigDecimal newPrice = setNewPrice(request, originalItem);
        Integer newAvailableQuantity = setNewQuantity(request, originalItem);
        Item newItem = new Item(newItemName, newPrice, newAvailableQuantity);
        return (database.accessItemDatabase().getAllItems().contains(newItem))
                ? Optional.of(new CoreError(FIELD_BUTTON, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdIsPresent(String id) {
        return (id == null || id.isBlank())
                ? Optional.of(new CoreError(FIELD_ID, ERROR_ID_MISSING))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsNumber(String value, String field, String errorMessage) {
        return (value != null && !value.isBlank() &&
                !value.matches(REGEX_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsPositive(String value, String field, String errorMessage) {
        return (value != null && !value.isBlank() &&
                !value.matches(REGEX_NOT_NEGATIVE_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsNotDecimal(String value, String field, String errorMessage) {
        return (value != null && !value.isBlank() &&
                !value.matches(REGEX_NOT_DECIMAL_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdExistsInShop(String id) {
        return (id != null && !id.isBlank() &&
                database.accessItemDatabase().findById(Long.parseLong(id)).isEmpty())
                ? Optional.of(new CoreError(FIELD_ID, ERROR_ID_NOT_EXISTS))
                : Optional.empty();
    }

    //TODO yeet, duplicate
    private Item getItemById(Long itemId) {
        return database.accessItemDatabase().findById(itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    private String setNewItemName(ChangeItemDataRequest request, Item originalItem) {
        return (newValueExists(request.getNewItemName()))
                ? request.getNewItemName()
                : originalItem.getName();
    }

    private BigDecimal setNewPrice(ChangeItemDataRequest request, Item originalItem) {
        return (newValueExists(request.getNewPrice()))
                ? new BigDecimal(request.getNewPrice()).setScale(2, RoundingMode.HALF_UP)
                : originalItem.getPrice();
    }

    private Integer setNewQuantity(ChangeItemDataRequest request, Item originalItem) {
        return (newValueExists(request.getNewAvailableQuantity()))
                ? Integer.parseInt(request.getNewAvailableQuantity())
                : originalItem.getAvailableQuantity();
    }

    private boolean newValueExists(String value) {
        return value != null && !value.isBlank();
    }

}
