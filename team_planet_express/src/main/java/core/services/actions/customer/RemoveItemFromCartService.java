package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.domain.user.User;
import core.services.exception.ItemNotFoundException;
import core.services.cart.CartValidator;

import java.util.Optional;

public class RemoveItemFromCartService {

    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";

    private final Database database;
    private final User user;

    public RemoveItemFromCartService(Database database, User user) {
        this.database = database;
        this.user = user;
    }

    public void execute(String itemName) {
        Optional<Item> item = database.accessItemDatabase().findByName(itemName);
        if (item.isEmpty()) {
            throw new ItemNotFoundException(ERROR_NO_SUCH_ITEM_IN_SHOP);
        }
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), user.getId());
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
