package shop.acceptance.custom;

import org.junit.jupiter.api.Test;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuyAcceptanceTest extends CustomAcceptanceTest {

    @Test
    void shouldBeEmptyCart() {
        buyCart();
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUserId().getValue());
        assertTrue(cart.isPresent());
        List<CartItem> cartItem = getDatabase().accessCartItemDatabase().getAllCartItemsForCartId(cart.get().getId());
        assertTrue(cartItem.isEmpty());
    }
}
