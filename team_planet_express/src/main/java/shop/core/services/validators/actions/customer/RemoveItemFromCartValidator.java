package shop.core.services.validators.actions.customer;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveItemFromCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";

    private final Database database;
    private final CurrentUserIdValidator userIdValidator;
    private final CartValidator cartValidator;
    private final InputStringValidator inputStringValidator;
    private final DatabaseAccessValidator databaseAccessValidator;

    public RemoveItemFromCartValidator(Database database, CurrentUserIdValidator userIdValidator, CartValidator cartValidator, InputStringValidator inputStringValidator, DatabaseAccessValidator databaseAccessValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
        this.inputStringValidator = inputStringValidator;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemName(request.getItemName(), errors);
            if (errors.isEmpty()) {
                validateItemNameInCart(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, FIELD_NAME, VALUE_NAME_ITEM);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateItemNameInShop(itemName).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameInShop(String itemName) {
        return (database.accessItemDatabase().findByName(itemName).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_SHOP))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request) {
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        return (database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.getId()).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_CART))
                : Optional.empty();
    }

}
