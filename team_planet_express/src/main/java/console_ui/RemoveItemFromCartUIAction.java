package console_ui;

import cart_item.CartItem;
import database.CartItemDatabase;
import database.ItemDatabase;
import item.Item;
import user_input.UserCommunication;

import java.util.Optional;

public class RemoveItemFromCartUIAction implements UIAction {

    private static final String ACTION_NAME = "Remove item from the cart";

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to remove: ";
    private static final String MESSAGE_ITEM_REMOVED = "Item removed from your cart.";
    private static final String ERROR_NO_SUCH_ITEM = "Error: No such item in your cart.";

    private final ItemDatabase itemDatabase;
    private final CartItemDatabase cartItemDatabase;
    private final UserCommunication userCommunication;

    public RemoveItemFromCartUIAction(ItemDatabase itemDatabase, CartItemDatabase cartItemDatabase, UserCommunication userCommunication) {
        this.itemDatabase = itemDatabase;
        this.cartItemDatabase = cartItemDatabase;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String userInputItem = userCommunication.getItem();
        if (itemInCart(userInputItem)) {
            CartItem cartItem = findCartItemByName(userInputItem).get();
            Item item = findItemByName(userInputItem).get();
            Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
            cartItemDatabase.deleteByID(cartItem.getId());
            itemDatabase.changeAvailableQuantity(item.getId(), newAvailableQuantity);
            userCommunication.informUser(MESSAGE_ITEM_REMOVED);
        } else {
            userCommunication.informUser(ERROR_NO_SUCH_ITEM);
        }
    }

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

    private boolean itemInCart(String itemName) {
        return findCartItemByName(itemName).isPresent();
    }

    private Optional<CartItem> findCartItemByName(String itemName) {
        return cartItemDatabase.getAllCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getName().equals(itemName))
                .findFirst();
    }

    private Optional<Item> findItemByName(String itemName) {
        return itemDatabase.getAllItems().stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();
    }

}
