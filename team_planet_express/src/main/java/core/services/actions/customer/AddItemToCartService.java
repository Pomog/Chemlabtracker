package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.AddItemToCartRequest;
import core.responses.CoreError;
import core.responses.customer.AddItemToCartResponse;
import core.services.cart.CartValidator;
import core.services.validators.customer.AddItemToCartValidator;
import core.support.MutableLong;

import java.util.List;
import java.util.Optional;

public class AddItemToCartService {

    private final Database database;
    private final AddItemToCartValidator validator;
    private final MutableLong currentUserId;
    private final CartValidator cartValidator;

    public AddItemToCartService(Database database, AddItemToCartValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.validator = validator;
        this.currentUserId = currentUserId;
        this.cartValidator = new CartValidator(database);
    }

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        //TODO make beegboi smol
        Optional<CoreError> error = cartValidator.validateOpenCartExistsForUserId(currentUserId.getValue());
        if (error.isPresent()) {
            return new AddItemToCartResponse(List.of(error.get()));
        }
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        String itemName = request.getItemName();
        Integer orderedQuantity = Integer.parseInt(request.getOrderedQuantity());
        Optional<Item> item = database.accessItemDatabase().findByName(itemName);
        addItemToCart(cart, item.get(), orderedQuantity);
        changeItemAvailability(item.get(), orderedQuantity);
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

}
