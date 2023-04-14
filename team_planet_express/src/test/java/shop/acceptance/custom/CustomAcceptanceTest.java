package shop.acceptance.custom;

import shop.ApplicationContext;
import shop.acceptance.ApplicationContextSetup;
import shop.core.database.Database;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUserId;

public abstract class CustomAcceptanceTest {
    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();

    protected void addItemToCart(String itemName, String quantity) {
        AddItemToCartService addItemToCartService = applicationContext.getBean(AddItemToCartService.class);
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(applicationContext.getBean(CurrentUserId.class), itemName, quantity);
        addItemToCartService.execute(addItemToCartRequest);
    }

    protected void buyCart() {
        BuyService buyService = applicationContext.getBean(BuyService.class);
        BuyRequest buyRequest = new BuyRequest(applicationContext.getBean(CurrentUserId.class));
        buyService.execute(buyRequest);
    }


    protected Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

    protected CurrentUserId getCurrentUserId() {
        return applicationContext.getBean(CurrentUserId.class);
    }

}
