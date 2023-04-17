package shop.acceptance.custom.tester;

import shop.ApplicationContext;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListShopItemsTester extends Tester {

    private ListShopItemsResponse listShopItemsResponse;

    public ListShopItemsTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public ListShopItemsTester showListShopItems() {
        ListShopItemsService listShopItemsService = applicationContext.getBean(ListShopItemsService.class);
        ListShopItemsRequest listShopItemsRequest = new ListShopItemsRequest();
        listShopItemsResponse = listShopItemsService.execute(listShopItemsRequest);
        return this;
    }

    public ListShopItemsTester checkItemInListShopResponse(String itemName, int quantity) {
        assertTrue(listShopItemsResponse.getShopItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;
    }

    public ListShopItemsTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }
}
