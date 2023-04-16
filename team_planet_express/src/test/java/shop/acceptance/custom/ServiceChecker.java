package shop.acceptance.custom;

import shop.ApplicationContext;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceChecker {
    private final ApplicationContext applicationContext;

    public ServiceChecker(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    protected void checkItemInCart(ListCartItemsResponse listCartItemsResponse, String itemName, Integer quantity) {
        Optional<Item> itemOptional = getDatabase().accessItemDatabase().getAllItems().stream()
                .filter(item -> item.getName().equals(itemName)).findFirst();
        assertTrue(itemOptional.isPresent());
        assertTrue(listCartItemsResponse.getCartItems().stream()
                .anyMatch(item -> item.getItemId().equals(itemOptional.get().getId()) && item.getOrderedQuantity().equals(quantity)));
    }

    protected void compareCartItem(String itemName, int quantity) {
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUserId().getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                cart.get().getId(),
                getDatabase().accessItemDatabase().findByName(itemName).get().getId()
        );
        assertTrue(cartItem.isPresent());
        assertTrue(cartItem.get().getItemId() != quantity);
    }

    protected void checkItemInListShop(ListShopItemsResponse listShopItemsResponse, String itemName, int quantity) {
        assertTrue(listShopItemsResponse.getShopItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
    }

    public void NotItemInCart(String itemName) {
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUserId().getValue());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                    cart.get().getId(),
                    getDatabase().accessItemDatabase().findByName(itemName).get().getId()
            );
            assertTrue(cartItem.isEmpty());
        }
    }

    private Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

    private CurrentUserId getCurrentUserId() {
        return applicationContext.getBean(CurrentUserId.class);
    }
}
