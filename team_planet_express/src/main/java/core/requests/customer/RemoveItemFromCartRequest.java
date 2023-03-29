package core.requests.customer;

public class RemoveItemFromCartRequest {

    private final Long userId;
    private final String itemName;

    public RemoveItemFromCartRequest(Long userId, String itemName) {
        this.userId = userId;
        this.itemName = itemName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getItemName() {
        return itemName;
    }

}
