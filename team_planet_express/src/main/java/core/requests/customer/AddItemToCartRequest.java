package core.requests.customer;

public class AddItemToCartRequest {

    private final String itemName;
    private final String orderedQuantity;

    public AddItemToCartRequest(String itemName, String orderedQuantity) {
        this.itemName = itemName;
        this.orderedQuantity = orderedQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

}
