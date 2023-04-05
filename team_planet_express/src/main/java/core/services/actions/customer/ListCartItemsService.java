package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.requests.customer.ListCartItemsRequest;
import core.responses.CoreError;
import core.responses.customer.ListCartItemsResponse;
import core.services.cart.CartService;
import core.services.validators.actions.customer.ListCartItemValidator;
import core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final ListCartItemValidator validator;
    private final DatabaseAccessValidator databaseAccessValidator;
    private final CartService cartService;

    public ListCartItemsService(Database database, ListCartItemValidator validator, DatabaseAccessValidator databaseAccessValidator, CartService cartService) {
        this.database = database;
        this.validator = validator;
        this.databaseAccessValidator = databaseAccessValidator;
        this.cartService = cartService;
    }

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        List<CartItem> cartItems = database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
        BigDecimal cartTotal = cartService.getSum(cart.getUserId());
        return new ListCartItemsResponse(cartItems, cartTotal);
    }

}
