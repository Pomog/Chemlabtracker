package services.actions.shop;

import database.Database;
import domain.cart.Cart;
import domain.cart_item.CartItem;
import domain.user.User;
import services.cart.CartService;
import validator.CartValidator;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final User user;

    public ListCartItemsService(Database database, User user) {
        this.database = database;
        this.user = user;
    }

    public List<CartItem> execute() {
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), user.getId());
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
    }

    //TODO this should not be here
    public BigDecimal getCartTotal() {
        return new CartService(database).getSum(database.accessCartDatabase().findOpenCartForUserId(user.getId()).get().getUserId());
    }

}
