package shop.acceptance.custom.tester;

import shop.ApplicationContext;
import shop.core.domain.item.Item;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCartItemsTester extends Tester {

    private ListCartItemsResponse listCartItemsResponse;

    public ListCartItemsTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public ListCartItemsTester showListCartItems() {
        ListCartItemsService listCartItemsService = applicationContext.getBean(ListCartItemsService.class);
        ListCartItemsRequest listCartItemsRequest = new ListCartItemsRequest(getCurrentUserId());
        listCartItemsResponse = listCartItemsService.execute(listCartItemsRequest);
        return this;
    }

    public ListCartItemsTester checkItemInCartResponse(String itemName, int quantity) {
        Optional<Item> itemOptional = getDatabase().accessItemDatabase().getAllItems().stream()
                .filter(item -> item.getName().equals(itemName)).findFirst();
        assertTrue(itemOptional.isPresent());
        assertTrue(listCartItemsResponse.getCartItems().stream()
                .anyMatch(item -> item.getItemId().equals(itemOptional.get().getId()) && item.getOrderedQuantity().equals(quantity)));
        return this;
    }

    @Override
    public ListCartItemsTester checkItemInCart(String itemName, Integer quantity) {
        super.checkItemInCart(itemName, quantity);
        return this;
    }
}
