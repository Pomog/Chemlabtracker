package shop.acceptance.guest;

import org.junit.jupiter.api.Test;
import shop.ApplicationContext;
import shop.acceptance.ApplicationContextSetup;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpAndOrderAnItemAcceptanceTest {

    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();

    @Test
    void shouldHaveAnItemInTheCorrectCart() {
        SignUpRequest signUpRequest = new SignUpRequest(getCurrentUserId(), "Brannigan", "captain", "password");
        SignUpResponse signUpResponse = getSignUpService().execute(signUpRequest);
        User newUser = signUpResponse.getUser();
        assertEquals(UserRole.CUSTOMER, newUser.getUserRole());
        assertTrue(getDatabase().accessCartDatabase().findOpenCartForUserId(newUser.getId()).isPresent());
        Item orderedItem = getDatabase().accessItemDatabase().findByName("Lightspeed Briefs").orElseThrow();
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(getCurrentUserId(), orderedItem.getName(), "1");
        getAddItemToCartService().execute(addItemToCartRequest);
        Cart userCart = getDatabase().accessCartDatabase().findOpenCartForUserId(newUser.getId()).get();
        List<CartItem> cartItems = getDatabase().accessCartItemDatabase().getAllCartItemsForCartId(userCart.getId());
        assertEquals(1, cartItems.size());
        assertTrue(getDatabase().accessCartItemDatabase().findByCartIdAndItemId(userCart.getId(), orderedItem.getId()).isPresent());
        CartItem cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(userCart.getId(), orderedItem.getId()).get();
        Item itemInTheCart = getDatabase().accessItemDatabase().findById(cartItem.getItemId()).orElseThrow();
        assertEquals("Lightspeed Briefs", itemInTheCart.getName());
        assertEquals(1, cartItem.getOrderedQuantity());
        assertEquals(2, orderedItem.getAvailableQuantity());
    }

    private CurrentUserId getCurrentUserId() {
        return applicationContext.getBean(CurrentUserId.class);
    }

    private SignUpService getSignUpService() {
        return applicationContext.getBean(SignUpService.class);
    }

    private Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

    private AddItemToCartService getAddItemToCartService() {
        return applicationContext.getBean(AddItemToCartService.class);
    }

}
