package shop.acceptance.custom;

import org.junit.jupiter.api.Test;

public class AcceptanceTestCustomService extends CustomAcceptanceTest {

    @Test
    void shouldBuyItem() {
        String itemName = "Lightspeed Briefs";
        Integer quantity = 3;

        checkItemInListShopItems(itemName, quantity);
        addAndCheckItemInCart(itemName, 2, quantity);
        checkItemInListCartItems(itemName, 2);
        checkRemoveItemFromCart(itemName);
        checkItemInListShopItems(itemName, quantity);
        addAndCheckItemInCart(itemName, 1, quantity);
        checkItemInListCartItems(itemName, 1);
        checkBuyCart();
        checkItemInListShopItems(itemName, 2);
    }
}
