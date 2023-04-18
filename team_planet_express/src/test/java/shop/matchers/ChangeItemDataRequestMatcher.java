package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.manager.ChangeItemDataRequest;

public class ChangeItemDataRequestMatcher implements ArgumentMatcher<ChangeItemDataRequest> {

    private final String itemId;
    private final String newItemName;
    private final String newPrice;
    private final String newAvailableQuantity;

    public ChangeItemDataRequestMatcher(String itemId, String newItemName, String newPrice, String newAvailableQuantity) {
        this.itemId = itemId;
        this.newItemName = newItemName;
        this.newPrice = newPrice;
        this.newAvailableQuantity = newAvailableQuantity;
    }

    @Override
    public boolean matches(ChangeItemDataRequest request) {
        return itemId.equals(request.getItemId()) &&
                newItemName.equals(request.getNewItemName()) &&
                newPrice.equals(request.getNewPrice()) &&
                newAvailableQuantity.equals(request.getNewAvailableQuantity());
    }

}
