package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.CoreError;
import core.responses.customer.RemoveItemFromCartResponse;
import core.services.validators.actions.customer.RemoveItemFromCartValidator;
import core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

public class RemoveItemFromCartService {

    private final Database database;
    private final RemoveItemFromCartValidator validator;
    private final DatabaseAccessValidator databaseAccessValidator;

    public RemoveItemFromCartService(Database database, RemoveItemFromCartValidator validator, DatabaseAccessValidator databaseAccessValidator) {
        this.database = database;
        this.validator = validator;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        CartItem cartItem = databaseAccessValidator.getCartItemByCartIdAndItemId(cart.getId(), item.getId());
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        database.accessCartItemDatabase().deleteByID(cartItem.getId());
        database.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

}
