package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.AddItemToCartRequest;
import core.responses.CoreError;
import core.responses.customer.AddItemToCartResponse;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.actions.customer.AddItemToCartValidator;
import core.support.MutableLong;

import java.util.List;
import java.util.Optional;

public class AddItemToCartService {

    private final Database database;
    private final AddItemToCartValidator validator;
    private final MutableLong currentUserId;

    public AddItemToCartService(Database database, AddItemToCartValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.validator = validator;
        this.currentUserId = currentUserId;
    }

    public MutableLong getCurrentUserId() {
        return currentUserId;
    }

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Cart cart = getOpenCartForUserId();
        Item item = getItemByName(request.getItemName());
        Integer orderedQuantity = Integer.parseInt(request.getOrderedQuantity());
        addItemToCart(cart, item, orderedQuantity);
        changeItemAvailability(item, orderedQuantity);
        return new AddItemToCartResponse();
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

    //TODO duplicate everywhere
    //TODO WTB Autowired
    private Cart getOpenCartForUserId() {
        return database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue())
                .orElseThrow(ServiceMissingDataException::new);
    }

    private Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
