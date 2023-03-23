package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.services.cart.CartService;
import core.services.cart.CartValidator;
import core.support.MutableLong;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final MutableLong currentUserId;

    public ListCartItemsService(Database database, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
    }

    public List<CartItem> execute() {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), currentUserId.getValue());
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
    }

    //TODO this should not be here
    public BigDecimal getCartTotal() {
        return new CartService(database).getSum(database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get().getUserId());
    }

}
