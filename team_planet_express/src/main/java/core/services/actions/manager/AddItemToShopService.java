package core.services.actions.manager;

import core.database.Database;
import core.domain.item.Item;
import core.services.exception.InvalidInputException;
import core.services.exception.InvalidQuantityException;
import core.services.exception.ItemAlreadyExistsException;

import java.math.BigDecimal;
import java.util.Optional;

/* this action not changing quantity for an existing item is on purpose */
/* we have another action for that */
public class AddItemToShopService {

    private static final String ERROR_ITEM_EXISTS = "Error: Item with this name already exists.";
    private static final String ERROR_NOT_NUMBER = "Error: String found where number was expected.";
    /* this error is trash, but validators will invalidate this anyway */
    private static final String ERROR_QUANTITY_NOT_POSITIVE = "Error: Quantity should be more than zero.";

    private final Database database;

    public AddItemToShopService(Database database) {
        this.database = database;
    }

    public void execute(String itemName, String stringPrice, String stringAvailableQuantity) {
        try {
            Integer availableQuantity = Integer.parseInt(stringAvailableQuantity);
            BigDecimal price = new BigDecimal(stringPrice);
            Optional<Item> item = database.accessItemDatabase().findByName(itemName);
            if (item.isPresent()) {
                throw new ItemAlreadyExistsException(ERROR_ITEM_EXISTS);
            }
            if (availableQuantity <= 0) {
                throw new InvalidQuantityException(ERROR_QUANTITY_NOT_POSITIVE);
            }
            database.accessItemDatabase().save(new Item(itemName, price, availableQuantity));
        } catch (NumberFormatException exception) {
            throw new InvalidInputException(ERROR_NOT_NUMBER, exception);
        }
    }

}
