package shop.acceptance.custom.tester;

import shop.dependency_injection.ApplicationContext;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.support.CurrentUserId;

public class AddItemToCartTester extends Tester {

    private String itemName;
    private Integer quantity;

    public AddItemToCartTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public AddItemToCartTester add(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        AddItemToCartService addItemToCartService = applicationContext.getBean(AddItemToCartService.class);
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(applicationContext.getBean(CurrentUserId.class), itemName, quantity.toString());
        addItemToCartService.execute(addItemToCartRequest);
        return this;
    }

    public AddItemToCartTester checkItemInCart() {
        super.checkItemInCart(itemName, quantity);
        return this;
    }

    public AddItemToCartTester checkItemInShop(int leftInShopQuantity) {
        super.checkItemInShop(itemName, leftInShopQuantity);
        return this;
    }
}
