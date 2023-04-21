package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.cart_item.CartItem;

public class CartItemMatcher implements ArgumentMatcher<CartItem> {

    private final Long cartId;
    private final Long itemId;
    private final Integer orderedQuantity;

    public CartItemMatcher(Long cartId, Long itemId, Integer orderedQuantity) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.orderedQuantity = orderedQuantity;
    }

    @Override
    public boolean matches(CartItem cartItem) {
        return cartId.equals(cartItem.getCartId()) &&
                itemId.equals(cartItem.getItemId()) &&
                orderedQuantity.equals(cartItem.getOrderedQuantity());
    }

}
