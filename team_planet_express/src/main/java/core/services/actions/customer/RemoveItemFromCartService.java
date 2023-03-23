package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.services.cart.CartValidator;
import core.services.exception.ItemNotFoundException;
import core.support.MutableLong;

import java.util.Optional;

public class RemoveItemFromCartService {

    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";

    private final Database database;
    private final MutableLong currentUserId;

    public RemoveItemFromCartService(Database database, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
    }

    public void execute(String itemName) {
        Optional<Item> item = database.accessItemDatabase().findByName(itemName);
        if (item.isEmpty()) {
            throw new ItemNotFoundException(ERROR_NO_SUCH_ITEM_IN_SHOP);
        }
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), currentUserId.getValue());
        Optional<CartItem> cartItem = database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.get().getId());
        if (cartItem.isEmpty()) {
            throw new ItemNotFoundException(ERROR_NO_SUCH_ITEM_IN_CART);
        }
        Integer newAvailableQuantity =
                item.get().getAvailableQuantity() + cartItem.get().getOrderedQuantity();
        database.accessCartItemDatabase().deleteByID(cartItem.get().getId());
        database.accessItemDatabase().changeAvailableQuantity(item.get().getId(), newAvailableQuantity);
    }

}
