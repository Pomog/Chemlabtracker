package core.services.cart;

import core.database.Database;
import core.domain.item.Item;
import core.services.exception.ServiceMissingDataException;

import java.math.BigDecimal;

public class CartService {

    private final Database database;

    public CartService(Database database) {
        this.database = database;
    }

    public BigDecimal getSum(Long cartId) {
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cartId)
                .stream()
                .map(cartItem -> getItemById(cartItem.getItemId()).getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Item getItemById(Long itemId) {
        return database.accessItemDatabase().findById(itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
