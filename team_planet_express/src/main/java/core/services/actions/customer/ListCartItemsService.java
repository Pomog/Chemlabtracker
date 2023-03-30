package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.requests.customer.ListCartItemsRequest;
import core.responses.CoreError;
import core.responses.customer.ListCartItemsResponse;
import core.services.cart.CartService;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.actions.customer.ListCartItemValidator;
import core.support.MutableLong;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final ListCartItemValidator validator;

    //TODO constructorize aka mockableize
    //TODO this should not be here to begin with..
    private final CartService cartService;

    public ListCartItemsService(Database database, ListCartItemValidator validator) {
        this.database = database;
        this.validator = validator;
        this.cartService = new CartService(database);
    }

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        Cart cart = getOpenCartForUserId(request.getUserId());
        List<CartItem> cartItems = database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
        BigDecimal cartTotal = cartService.getSum(cart.getUserId());
        return new ListCartItemsResponse(cartItems, cartTotal);
    }

    //TODO yeet, duplicate
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
