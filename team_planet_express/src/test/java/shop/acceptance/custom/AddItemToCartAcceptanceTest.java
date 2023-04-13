package shop.acceptance.custom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.ApplicationContext;
import shop.acceptance.ApplicationContextSetup;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddItemToCartAcceptanceTest {

    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();

    @BeforeEach
    void makeAddItemToCart() {
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(applicationContext.getBean(CurrentUserId.class), "Lightspeed Briefs", "2");
        getAddItemToCartService().execute(addItemToCartRequest);
    }

    @Test
    void shouldAddItemsToCart() {
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                cart.get().getId(),
                getDatabase().accessItemDatabase().findByName("Lightspeed Briefs").get().getId()
        );
        assertTrue(cartItem.isPresent());
    }

    @Test
    void shouldDecreaseFromTheShop() {
        Item item = getDatabase().accessItemDatabase().findByName("Lightspeed Briefs").get();
        assertEquals(item.getAvailableQuantity(), 1);
    }

    private AddItemToCartService getAddItemToCartService() {
        return applicationContext.getBean(AddItemToCartService.class);
    }

    private Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }
}
