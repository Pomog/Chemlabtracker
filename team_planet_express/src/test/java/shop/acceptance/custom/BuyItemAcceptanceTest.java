package shop.acceptance.custom;

import org.junit.jupiter.api.Test;

public class BuyItemAcceptanceTest extends CustomAcceptanceTest {

    @Test
    void shouldBuyItem() {

        listShopItems()
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 3)
                .checkItemInShop("Lightspeed Briefs", 3);
        addItemToCart()
                .add("Lightspeed Briefs", 2)
                .checkItemInCart()
                .checkItemInShop(1);
        listShopItems()
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 1);
        removeItemFromCart()
                .remove("Lightspeed Briefs")
                .notItemInCart()
                .checkItemInShop("Lightspeed Briefs", 3);
        listShopItems()
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 3)
                .checkItemInShop("Lightspeed Briefs", 3);
        addItemToCart()
                .add("Lightspeed Briefs", 2).checkItemInCart()
                .checkItemInShop(1);
        listCartItems()
                .showListCartItems()
                .checkItemInCart("Lightspeed Briefs", 2)
                .checkItemInCartResponse("Lightspeed Briefs", 2);
        buyCart()
                .buy()
                .checkCartIsClosed();
        listShopItems()
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 1)
                .checkItemInShop("Lightspeed Briefs", 1);
    }
}
