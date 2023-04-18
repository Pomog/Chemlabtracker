package shop.core.services.validators.actions.manager;

import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

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
    private static final String VALUE_NAME_ID = "Item id";
    private static final String VALUE_NAME_PRICE = "Price";
    private static final String VALUE_NAME_QUANTITY = "Quantity";
    private static final String ERROR_ID_NOT_EXISTS = "Error: Item with this id does not exist.";
    private static final String ERROR_ITEM_EXISTS = "Error: Exactly the same item already exists.";

    private final Database database;
    private final InputStringValidator inputStringValidator;
    private final DatabaseAccessValidator databaseAccessValidator;

    public ChangeItemDataValidator(Database database, InputStringValidator inputStringValidator, DatabaseAccessValidator databaseAccessValidator) {
        this.database = database;
        this.inputStringValidator = inputStringValidator;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public List<CoreError> validate(ChangeItemDataRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request.getItemId(), errors);
        validatePrice(request.getNewPrice(), errors);
        validateQuantity(request.getNewAvailableQuantity(), errors);
        if (errors.isEmpty()) {
            validateDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateId(String id, List<CoreError> errors) {
        InputStringValidatorData record = new InputStringValidatorData(id, FIELD_ID, VALUE_NAME_ID);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotDecimal(record).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateIdExistsInShop(id).ifPresent(errors::add);
        }
    }

    private void validatePrice(String price, List<CoreError> errors) {
        InputStringValidatorData record = new InputStringValidatorData(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

    private void validateQuantity(String availableQuantity, List<CoreError> errors) {
        InputStringValidatorData record = new InputStringValidatorData(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotDecimal(record).ifPresent(errors::add);
    }

    private Optional<CoreError> validateDuplicate(ChangeItemDataRequest request) {
        Item originalItem = databaseAccessValidator.getItemById(Long.parseLong(request.getItemId()));
        String newItemName = setNewItemName(request, originalItem);
        BigDecimal newPrice = setNewPrice(request, originalItem);
        return (database.accessItemDatabase().getAllItems().stream()
                .filter(item -> !originalItem.getId().equals(item.getId()))
                .anyMatch(item -> newItemName.equals(item.getName()) && newPrice.compareTo(item.getPrice()) == 0))
                ? Optional.of(new CoreError(FIELD_BUTTON, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdExistsInShop(String id) {
        return (id != null && !id.isBlank() &&
                database.accessItemDatabase().findById(Long.parseLong(id)).isEmpty())
                ? Optional.of(new CoreError(FIELD_ID, ERROR_ID_NOT_EXISTS))
                : Optional.empty();
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

    private boolean newValueExists(String value) {
        return value != null && !value.isBlank();
    }

}
