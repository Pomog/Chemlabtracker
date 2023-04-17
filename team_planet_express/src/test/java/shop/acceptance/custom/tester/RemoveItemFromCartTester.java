package shop.acceptance.custom.tester;

import shop.ApplicationContext;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.support.CurrentUserId;

public class RemoveItemFromCartTester extends Tester {
    private String itemName;

    public RemoveItemFromCartTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public RemoveItemFromCartTester remove(String itemName) {
        this.itemName = itemName;
        RemoveItemFromCartService removeItemFromCartService = applicationContext.getBean(RemoveItemFromCartService.class);
        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest(applicationContext.getBean(CurrentUserId.class), itemName);
        removeItemFromCartService.execute(removeItemFromCartRequest);
        return this;
    }

    public RemoveItemFromCartTester notItemInCart() {
        super.notItemInCart(itemName);
        return this;
    }

    public RemoveItemFromCartTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }
}
