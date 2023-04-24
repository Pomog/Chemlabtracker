package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.time.LocalDate;

public class CartMatcher implements ArgumentMatcher<Cart> {

    private final Long userId;
    private final CartStatus cartStatus;
    private final LocalDate lastActionDate;

    public CartMatcher(Long userId, CartStatus cartStatus, LocalDate lastActionDate) {
        this.userId = userId;
        this.cartStatus = cartStatus;
        this.lastActionDate = lastActionDate;
    }

    @Override
    public boolean matches(Cart cart) {
        return userId.equals(cart.getUserId()) &&
                cartStatus.equals(cart.getCartStatus()) &&
                lastActionDate.equals(cart.getLastActionDate());
    }

}
