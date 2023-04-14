package shop.acceptance.guest;

import org.junit.jupiter.api.Test;
import shop.acceptance.AcceptanceTest;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.responses.guest.SignUpResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpAndOrderAnItemAcceptanceTest extends AcceptanceTest {

    @Test
    void shouldBecomeCustomerAndHaveAnItemInTheCart() {
        SignUpResponse signUpResponse = executeSignUp("Brannigan", "captain", "password");
        assertFalse(signUpResponse.hasErrors());
        User newUser = signUpResponse.getUser();
        assertEquals(UserRole.CUSTOMER, newUser.getUserRole());
        assertTrue(getDatabase().accessCartDatabase().findOpenCartForUserId(newUser.getId()).isPresent());
        AddItemToCartResponse addItemToCartResponse = executeAddItemToCart("Lightspeed Briefs", "1");
        assertFalse(addItemToCartResponse.hasErrors());
        Cart userCart = getDatabase().accessCartDatabase().findOpenCartForUserId(newUser.getId()).get();
        List<CartItem> cartItems = getDatabase().accessCartItemDatabase().getAllCartItemsForCartId(userCart.getId());
        assertEquals(1, cartItems.size());
        CartItem cartItem = cartItems.get(0);
        Item originalItem = getDatabase().accessItemDatabase().findById(cartItem.getItemId()).orElseThrow();
        assertEquals("Lightspeed Briefs", originalItem.getName());
        assertEquals(1, cartItem.getOrderedQuantity());
        assertEquals(2, originalItem.getAvailableQuantity());
    }

}
