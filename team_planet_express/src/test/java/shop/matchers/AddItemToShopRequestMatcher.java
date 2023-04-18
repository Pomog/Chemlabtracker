package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.manager.AddItemToShopRequest;

public class AddItemToShopRequestMatcher implements ArgumentMatcher<AddItemToShopRequest> {

    private final String itemName;
    private final String price;
    private final String availableQuantity;

    public AddItemToShopRequestMatcher(String itemName, String price, String availableQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean matches(AddItemToShopRequest request) {
        return itemName.equals(request.getItemName()) &&
                price.equals(request.getPrice()) &&
                availableQuantity.equals(request.getAvailableQuantity());
    }

}
