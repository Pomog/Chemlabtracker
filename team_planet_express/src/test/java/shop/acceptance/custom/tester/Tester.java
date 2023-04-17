package shop.acceptance.custom.tester;

import shop.ApplicationContext;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Tester {
    protected final ApplicationContext applicationContext;
    public Tester(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected Tester checkItemInCart(String itemName, Integer quantity){
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUserId().getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                cart.get().getId(),
                getDatabase().accessItemDatabase().findByName(itemName).get().getId()
        );
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(getDatabase().accessItemDatabase().getAllItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }


    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUserId().getValue());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                    cart.get().getId(),
                    getDatabase().accessItemDatabase().findByName(itemName).get().getId()
            );
            assertTrue(cartItem.isEmpty());
        }
        return this;

    }

    protected Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

    protected CurrentUserId getCurrentUserId() {
        return applicationContext.getBean(CurrentUserId.class);
    }
}
