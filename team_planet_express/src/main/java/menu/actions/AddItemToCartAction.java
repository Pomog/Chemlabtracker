package menu.actions;

import cart.Cart;
import data.Item;
import shop.Shop;
import user_input.UserCommunication;

public class AddItemToCartAction {

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to order: ";
    private static final String PROMPT_TOPIC_QUANTITY = " quantity to be ordered: ";
    private static final String MESSAGE_ITEM_ADDED = "Item added to your cart";
    private static final String MESSAGE_NO_SUCH_ITEM = "Error: No such item.";
    private static final String MESSAGE_NOT_ENOUGH_QUANTITY = "Error: Available quantity lower than ordered amount.";

    private final UserCommunication userCommunication = new UserCommunication();

    public void run(Shop shop, Cart cart) {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String userInputItem = userCommunication.getItem();
        if (shop.itemAvailable(userInputItem)) {
            Item item = shop.findByName(userInputItem).get();
            userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
            Integer orderedQuantity = userCommunication.getQuantity();
            if (orderQuantityValid(orderedQuantity, item)) {
                cart.addItem(item, orderedQuantity, shop);
                userCommunication.informUser(MESSAGE_ITEM_ADDED);
            } else {
                userCommunication.informUser(MESSAGE_NOT_ENOUGH_QUANTITY);
            }
        } else {
            userCommunication.informUser(MESSAGE_NO_SUCH_ITEM);
        }
    }

    private boolean orderQuantityValid(Integer quantityOrdered, Item item) {
        return quantityOrdered > 0 &&
                quantityOrdered <= item.getQuantityAvailable();
    }

}
