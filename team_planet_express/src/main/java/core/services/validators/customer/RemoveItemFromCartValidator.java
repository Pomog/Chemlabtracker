package core.services.validators.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.item.Item;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.CoreError;
import core.services.cart.CartValidator;
import core.services.exception.ServiceMissingDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveItemFromCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";
    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;
    private final CartValidator cartValidator;

    public RemoveItemFromCartValidator(Database database, CartValidator cartValidator) {
        this.database = database;
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            //TODO order of validations seems borked
            validateCartIsNotEmpty(request.getUserId()).ifPresent(errors::add);
            validateItemNameInCart(request).ifPresent(errors::add);
            if (errors.isEmpty()) {
                validateItemNameInShop(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request) {
        Cart cart = getOpenCartForUserId(request.getUserId());
        Item item = getItemByName(request.getItemName());
        //TODO test for NPE on item.get()
        return (database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.getId()).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_CART))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameInShop(RemoveItemFromCartRequest request) {
        return (database.accessItemDatabase().findByName(request.getItemName()).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_SHOP))
                : Optional.empty();
    }

    //TODO unnecessary ?
    private Optional<CoreError> validateCartIsNotEmpty(Long userId) {
        Cart cart = getOpenCartForUserId(userId);
        return (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0)
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    private Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
