package shop.acceptance.custom;

import shop.dependency_injection.ApplicationContext;
import shop.acceptance.ApplicationContextSetup;
import shop.acceptance.custom.tester.*;

public abstract class CustomAcceptanceTest {
    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();


    protected AddItemToCartTester addItemToCart() {
        return new AddItemToCartTester(applicationContext);
    }

    protected ListShopItemsTester listShopItems() {
        return new ListShopItemsTester(applicationContext);
    }

    protected RemoveItemFromCartTester removeItemFromCart() {
        return new RemoveItemFromCartTester(applicationContext);
    }

    protected ListCartItemsTester listCartItems() {
        return new ListCartItemsTester(applicationContext);
    }

    protected BuyTester buyCart() {
        return new BuyTester(applicationContext);
    }

}
