package core.requests.customer;

public class RemoveItemFromCartRequest {
    private final String itemName;

    public RemoveItemFromCartRequest(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
