package core.requests.customer;

public class AddItemToCartRequest {

    private final Long userId;
    private final String itemName;
    private final String orderedQuantity;

    public AddItemToCartRequest(Long userId, String itemName, String orderedQuantity) {
        this.userId = userId;
        this.itemName = itemName;
        this.orderedQuantity = orderedQuantity;
    }

    public Long getUserId() {
        return userId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

}
