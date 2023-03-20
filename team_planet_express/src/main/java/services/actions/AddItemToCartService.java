package services.actions;

import database.Database;
import domain.cart.Cart;
import domain.cart_item.CartItem;
import domain.item.Item;
import services.exception.InvalidInputException;
import services.exception.InvalidQuantityException;
import services.exception.ItemNotFoundException;
import validator.CartValidator;

import java.util.Optional;

public class AddItemToCartService {

    private static final String ERROR_NOT_A_NUMBER = "Error: Quantity should be a number.";
    private static final String ERROR_NO_SUCH_ITEM = "Error: No such item.";
    private static final String ERROR_NOT_ENOUGH_QUANTITY = "Error: Available quantity lower than ordered amount.";

    private final Database database;
    private final Long userId;

    public AddItemToCartService(Database database, Long userId) {
        this.database = database;
        this.userId = userId;
    }

    public void execute(String itemName, String stringOrderedQuantity) {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), userId);
        try {
            Integer orderedQuantity = Integer.parseInt(stringOrderedQuantity);
            Optional<Item> item = database.accessItemDatabase().findByName(itemName);
            if (item.isEmpty()) {
                throw new ItemNotFoundException(ERROR_NO_SUCH_ITEM);
            }
            if (!orderedQuantityValid(orderedQuantity, item.get())) {
                throw new InvalidQuantityException(ERROR_NOT_ENOUGH_QUANTITY);
            }
            addItemToCart(cart, item.get(), orderedQuantity);
            changeItemAvailability(item.get(), orderedQuantity);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException(ERROR_NOT_A_NUMBER, exception);
        }
    }

    private void addItemToCart(Cart cart, Item item, Integer orderedQuantity) {
        Optional<CartItem> cartItem = database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.getId());
        if (cartItem.isEmpty()) {
            database.accessCartItemDatabase().save(new CartItem(cart.getId(), item.getId(), orderedQuantity));
        } else {
            Integer newCartItemQuantity = cartItem.get().getOrderedQuantity() + orderedQuantity;
            cartItem.get().setOrderedQuantity(newCartItemQuantity);
        }
    }

    private void changeItemAvailability(Item item, Integer orderedQuantity) {
        Integer newAvailableQuantity = item.getAvailableQuantity() - orderedQuantity;
        database.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
    }

    private boolean orderedQuantityValid(Integer quantityOrdered, Item item) {
        return quantityOrdered > 0 &&
                quantityOrdered <= item.getAvailableQuantity();
    }

}
