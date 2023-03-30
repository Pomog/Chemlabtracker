package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.RemoveItemFromCartRequest;
import core.responses.CoreError;
import core.responses.customer.RemoveItemFromCartResponse;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.customer.RemoveItemFromCartValidator;

import java.util.List;

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
        Cart cart = getOpenCartForUserId(request.getUserId());
        Item item = getItemByName(request.getItemName());
        CartItem cartItem = getCartItemByCartIdAndItemId(cart.getId(), item.getId());
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        database.accessCartItemDatabase().deleteByID(cartItem.getId());
        database.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

    //TODO duplicate everywhere
    //TODO WTB Autowired
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    private Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

    private CartItem getCartItemByCartIdAndItemId(Long cartId, Long itemId) {
        return database.accessCartItemDatabase().findByCartIdAndItemId(cartId, itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
