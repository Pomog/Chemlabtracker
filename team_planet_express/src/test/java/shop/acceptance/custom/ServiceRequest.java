package shop.acceptance.custom;

import shop.ApplicationContext;
import shop.core.requests.customer.*;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.customer.*;
import shop.core.support.CurrentUserId;

public class ServiceRequest {

    private final ApplicationContext applicationContext;

    public ServiceRequest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

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

    protected ListShopItemsResponse listShopItems() {
        ListShopItemsService listShopItemsService = applicationContext.getBean(ListShopItemsService.class);
        ListShopItemsRequest listShopItemsRequest = new ListShopItemsRequest();
        return listShopItemsService.execute(listShopItemsRequest);
    }

    protected ListCartItemsResponse listCartItems() {
        ListCartItemsService listCartItemsService = applicationContext.getBean(ListCartItemsService.class);
        ListCartItemsRequest listCartItemsRequest = new ListCartItemsRequest(applicationContext.getBean(CurrentUserId.class));
        return listCartItemsService.execute(listCartItemsRequest);
    }

    protected void removeItemFromCart(String itemName) {
        RemoveItemFromCartService removeItemFromCartService = applicationContext.getBean(RemoveItemFromCartService.class);
        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest(applicationContext.getBean(CurrentUserId.class), itemName);
        removeItemFromCartService.execute(removeItemFromCartRequest);
    }
}
