package shop.acceptance.custom.tester;

import shop.ApplicationContext;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTester extends Tester {
    private Cart cart;

    public BuyTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public BuyTester buy() {
        Database database = applicationContext.getBean(Database.class);
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        BuyService buyService = applicationContext.getBean(BuyService.class);
        BuyRequest buyRequest = new BuyRequest(applicationContext.getBean(CurrentUserId.class));
        buyService.execute(buyRequest);
        return this;
    }

    public BuyTester checkCartIsClosed() {
        assertEquals(cart.getCartStatus(), CartStatus.CLOSED);
        return this;
    }
}
