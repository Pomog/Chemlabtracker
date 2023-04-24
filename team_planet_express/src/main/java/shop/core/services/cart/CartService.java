package shop.core.services.cart;

import shop.core.database.Database;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.math.BigDecimal;

@DIComponent
public class CartService {

    @DIDependency
    private Database database;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;

    public BigDecimal getSum(Long cartId) {
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cartId)
                .stream()
                .map(cartItem -> databaseAccessValidator.getItemById(cartItem.getItemId()).getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
