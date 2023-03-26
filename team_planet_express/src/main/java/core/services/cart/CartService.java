package core.services.cart;

import core.database.Database;

import java.math.BigDecimal;

public class CartService {

    private final Database database;

    public CartService(Database database) {
        this.database = database;
    }

    public BigDecimal getSum(Long cartId) {
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cartId)
                .stream()
                .map(cartItem -> database.accessItemDatabase().findById(cartItem.getItemId()).get().getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
