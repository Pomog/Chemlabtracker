package console_ui;

import cart_item.CartItem;
import database.CartItemDatabase;
import database.ItemDatabase;
import item.Item;
import user_input.UserCommunication;

import java.util.InputMismatchException;
import java.util.Optional;

public class AddItemToCartUIAction implements UIAction {

    private static final String ACTION_NAME = "Add item to the cart";

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to order: ";
    private static final String PROMPT_TOPIC_QUANTITY = "quantity to be ordered: ";
    private static final String MESSAGE_ITEM_ADDED = "Item added to your cart.";
    private static final String ERROR_NO_SUCH_ITEM = "Error: No such item.";
    private static final String ERROR_NOT_A_NUMBER = "Error: Quantity should be a number.";
    private static final String ERROR_NOT_ENOUGH_QUANTITY = "Error: Available quantity lower than ordered amount.";

    private final ItemDatabase itemDatabase;
    private final CartItemDatabase cartItemDatabase;
    private final UserCommunication userCommunication;

    public AddItemToCartUIAction(ItemDatabase itemDatabase, CartItemDatabase cartItemDatabase, UserCommunication userCommunication) {
        this.itemDatabase = itemDatabase;
        this.cartItemDatabase = cartItemDatabase;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String userInputItem = userCommunication.getItem();
        if (itemAvailable(userInputItem)) {
            Item item = findByName(userInputItem).get();
            userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
            try {
                Integer orderedQuantity = userCommunication.getQuantity();
                if (orderedQuantityValid(orderedQuantity, item)) {
                    cartItemDatabase.save(new CartItem(item, orderedQuantity));
                    Integer newAvailableQuantity = item.getAvailableQuantity() - orderedQuantity;
                    itemDatabase.changeAvailableQuantity(item.getId(), newAvailableQuantity);
                    userCommunication.informUser(MESSAGE_ITEM_ADDED);
                } else {
                    userCommunication.informUser(ERROR_NOT_ENOUGH_QUANTITY);
                }
            } catch (InputMismatchException e) {
                userCommunication.informUser(ERROR_NOT_A_NUMBER);
            }
            userCommunication.clearBuffer();
        } else {
            userCommunication.informUser(ERROR_NO_SUCH_ITEM);
        }
    }

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

    private boolean itemAvailable(String itemName) {
        return itemExists(itemName) &&
                findByName(itemName).get().getAvailableQuantity() > 0;
    }

    private Optional<Item> findByName(String itemName) {
        return itemDatabase.getAllItems().stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();
    }

    private boolean itemExists(String itemName) {
        return findByName(itemName).isPresent();
    }

    private boolean orderedQuantityValid(Integer quantityOrdered, Item item) {
        return quantityOrdered > 0 &&
                quantityOrdered <= item.getAvailableQuantity();
    }

}
