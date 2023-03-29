package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.requests.customer.ListCartItemsRequest;
import core.responses.CoreError;
import core.responses.customer.ListCartItemsResponse;
import core.services.cart.CartService;
import core.services.validators.customer.ListCartItemValidator;
import core.support.MutableLong;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final ListCartItemValidator validator;
    private final MutableLong currentUserId;

    //TODO constructorize aka mockableize
    //TODO this should not be here to begin with..
    private final CartService cartService;

    public ListCartItemsService(Database database, ListCartItemValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.validator = validator;
        this.currentUserId = currentUserId;
        this.cartService = new CartService(database);
    }

    public MutableLong getCurrentUserId() {
        return currentUserId;
    }

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        List<CartItem> cartItems = database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
        BigDecimal cartTotal = cartService.getSum(cart.getUserId());
        return new ListCartItemsResponse(cartItems, cartTotal);
    }

}
