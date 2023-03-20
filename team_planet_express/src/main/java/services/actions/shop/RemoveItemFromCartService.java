package services.actions.shop;

import database.Database;
import domain.cart.Cart;
import domain.cart_item.CartItem;
import domain.item.Item;
import services.exception.ItemNotFoundException;
import validator.CartValidator;

import java.util.Optional;

public class RemoveItemFromCartService {

    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "Error: No such item in your cart.";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "Error: No such item in the shop.";

    private final Database database;
    private final Long userId;

    public RemoveItemFromCartService(Database database, Long userId) {
        this.database = database;
        this.userId = userId;
    }

    public void execute(String itemName) {
        Optional<Item> item = database.accessItemDatabase().findByName(itemName);
        if (item.isEmpty()) {
            throw new ItemNotFoundException(ERROR_NO_SUCH_ITEM_IN_SHOP);
        }
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), userId);
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
