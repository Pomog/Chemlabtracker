package core.services.validators.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.item.Item;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveItemFromCartValidator {
    private static final String FIELD_NAME = "name";

    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";

    private static final String ERROR_CART_EMPTY = "Error: Your cart is empty.";

    private final Database database;

    public RemoveItemFromCartValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(RemoveItemFromCartRequest request, Long userId) {
        List<CoreError> errors = new ArrayList<>();
        validateCartIsNotEmpty(userId).ifPresent(errors::add);
        validateItemNameInCart(request, userId).ifPresent(errors::add);
        if (errors.isEmpty())
            validateItemNameInShop(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request, Long userId) {
        Optional<Item> item = database.accessItemDatabase().findByName(request.getItemName());
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(userId).get();
        //TODO test for NPE on item.get()
        return (database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.get().getId()).isEmpty())
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
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(userId).get();
        return (database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()).size() == 0)
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

}
