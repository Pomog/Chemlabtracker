package shop.acceptance.custom;

import shop.ApplicationContext;
import shop.acceptance.ApplicationContextSetup;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class CustomAcceptanceTest {
    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();

    final ServiceRequest serviceRequest = new ServiceRequest(applicationContext);
    final ServiceChecker serviceChecker = new ServiceChecker(applicationContext);

    protected void checkItemInListShopItems(String itemName, int quantity) {
        ListShopItemsResponse listShopItemsResponse = serviceRequest.listShopItems();
        serviceChecker.checkItemInListShop(listShopItemsResponse, itemName, quantity);
    }

    protected void addAndCheckItemInCart(String itemName, Integer buyQuantity, Integer shopQuantity) {
        serviceRequest.addItemToCart(itemName, buyQuantity.toString());
        serviceChecker.compareCartItem(itemName, buyQuantity);
        checkItemInListShopItems(itemName, shopQuantity - buyQuantity);
    }

    protected void checkItemInListCartItems(String itemName, Integer quantity) {
        ListCartItemsResponse listCartItemsResponse = serviceRequest.listCartItems();
        serviceChecker.checkItemInCart(listCartItemsResponse, itemName, quantity);
    }

    protected void checkRemoveItemFromCart(String itemName) {
        serviceRequest.removeItemFromCart(itemName);
        serviceChecker.NotItemInCart(itemName);
    }

    protected void checkBuyCart() {
        Database database = applicationContext.getBean(Database.class);
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        Cart currentCart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        serviceRequest.buyCart();
        assertEquals(currentCart.getCartStatus(), CartStatus.CLOSED);
    }

}
