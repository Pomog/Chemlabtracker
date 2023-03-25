package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.responses.CoreError;
import core.responses.customer.ListCartItemsResponse;
import core.services.cart.CartService;
import core.services.cart.CartValidator;
import core.support.MutableLong;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//TODO this is ugly in case we still need ListCartItemValidator
public class ListCartItemsService {

    private final Database database;
    private final MutableLong currentUserId;
    //private final ListCartItemValidator validator;
    private final CartValidator cartValidator;

    public ListCartItemsService(Database database, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
        this.cartValidator = new CartValidator(database);
    }

    //    public ListCartItemsService(Database database, ListCartItemValidator validator, MutableLong currentUserId) {
//        this.database = database;
//        this.currentUserId = currentUserId;
//        this.validator = validator;
//        this.cartValidator = new CartValidator(database);
//    }

    public ListCartItemsResponse execute() {
        Optional<CoreError> error = cartValidator.validateOpenCartExistsForUserId(currentUserId.getValue());
        if (error.isPresent()) {
            return new ListCartItemsResponse(List.of(error.get()));
        }
//        List<CoreError> errors = validator.validate(currentUserId.getValue());
//        if (!errors.isEmpty()) {
//            return new ListCartItemsResponse(errors);
//        }
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        return new ListCartItemsResponse(database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId()), getCartTotal());
    }

    //TODO this should not be here
    private BigDecimal getCartTotal() {
        return new CartService(database).getSum(database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get().getUserId());
    }

}
