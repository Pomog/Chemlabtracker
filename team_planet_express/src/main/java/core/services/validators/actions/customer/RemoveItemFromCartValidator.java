package core.services.validators.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.item.Item;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.cart.CartValidator;
import core.services.validators.universal.system.LongUserIdValidator;
import core.services.validators.universal.user_input.PresenceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveItemFromCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";

    private final Database database;
    private final LongUserIdValidator userIdValidator;
    private final CartValidator cartValidator;
    private final PresenceValidator presenceValidator;
    private List<CoreError> errors;

    public RemoveItemFromCartValidator(Database database, LongUserIdValidator userIdValidator, CartValidator cartValidator, PresenceValidator presenceValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
        this.presenceValidator = presenceValidator;
    }

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        userIdValidator.validateLongUserIdIsPresent(request.getUserId());
        errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemName(request.getItemName());
            if (errors.isEmpty()) {
                validateItemNameInCart(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private void validateItemName(String itemName) {
        presenceValidator.validateStringIsPresent(itemName, FIELD_NAME, VALUE_NAME_ITEM).ifPresent(errors::add);
        validateItemNameInShop(itemName).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameInShop(String itemName) {
        return (database.accessItemDatabase().findByName(itemName).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_SHOP))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request) {
        Cart cart = getOpenCartForUserId(request.getUserId());
        Item item = getItemByName(request.getItemName());
        return (database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.getId()).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_CART))
                : Optional.empty();
    }

    //TODO yeet, duplicate
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    //TODO yeet, duplicate
    private Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
