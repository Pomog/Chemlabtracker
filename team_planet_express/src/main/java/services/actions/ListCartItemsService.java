package services.actions;

import database.Database;
import domain.cart.Cart;
import domain.cart_item.CartItem;
import services.cart.CartService;
import validator.CartValidator;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final Long userId;

    public ListCartItemsService(Database database, Long userId) {
        this.database = database;
        this.userId = userId;
    }

    public List<CartItem> execute() {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), userId);
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
    }

    public BigDecimal getCartTotal() {
        return new CartService(database).getSum(database.accessCartDatabase().findOpenCartForUserId(userId).get().getUserId());
    }

}
