package shop.acceptance.custom;

import org.junit.jupiter.api.Test;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddItemToCartAcceptanceTest extends CustomAcceptanceTest {

    @Test
    void shouldAddItemsToCart() {
        addItemToCart("Lightspeed Briefs", "2");
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUserId().getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                cart.get().getId(),
                getDatabase().accessItemDatabase().findByName("Lightspeed Briefs").get().getId()
        );
        assertTrue(cartItem.isPresent());
    }

    @Test
    void shouldDecreaseFromTheShop() {
        addItemToCart("Lightspeed Briefs", "2");
        Item item = getDatabase().accessItemDatabase().findByName("Lightspeed Briefs").get();
        assertEquals(item.getAvailableQuantity(), 1);
    }


}
