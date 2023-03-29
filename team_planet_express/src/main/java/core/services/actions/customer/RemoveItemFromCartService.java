package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.CoreError;
import core.responses.customer.RemoveItemFromCartResponse;
import core.services.validators.customer.RemoveItemFromCartValidator;

import java.util.List;
import java.util.Optional;

public class RemoveItemFromCartService {

    private final Database database;
    private final RemoveItemFromCartValidator validator;

    public RemoveItemFromCartService(Database database, RemoveItemFromCartValidator validator) {
        this.database = database;
        this.validator = validator;
    }


    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Optional<Item> item = database.accessItemDatabase().findByName(request.getItemName());
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(request.getUserId()).get();
        Optional<CartItem> cartItem = database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.get().getId());
        Integer newAvailableQuantity = item.get().getAvailableQuantity() + cartItem.get().getOrderedQuantity();
        database.accessCartItemDatabase().deleteByID(cartItem.get().getId());
        database.accessItemDatabase().changeAvailableQuantity(item.get().getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

}
