package core.services.cart;

import core.database.Database;
import core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;

public class CartService {

    private final Database database;
    private final DatabaseAccessValidator databaseAccessValidator;

    public CartService(Database database, DatabaseAccessValidator databaseAccessValidator) {
        this.database = database;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public BigDecimal getSum(Long cartId) {
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cartId)
                .stream()
                .map(cartItem -> databaseAccessValidator.getItemById(cartItem.getItemId()).getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
