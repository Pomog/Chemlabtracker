package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.responses.CoreError;
import core.responses.customer.ListCartItemsResponse;
import core.services.cart.CartService;
import core.services.cart.CartValidator;
import core.services.validators.customer.ListCartItemValidator;
import core.support.MutableLong;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final MutableLong currentUserId;
    private final ListCartItemValidator validator;

    public ListCartItemsService(Database database, ListCartItemValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
        this.validator = validator;
    }

    public ListCartItemsResponse execute() {
        List<CoreError> errors = validator.validate(currentUserId.getValue());
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        //TODO throws
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), currentUserId.getValue());
        return new ListCartItemsResponse(database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()), getCartTotal());
    }

    //TODO this should not be here
    private BigDecimal getCartTotal() {
        return new CartService(database).getSum(database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get().getUserId());
    }

}
