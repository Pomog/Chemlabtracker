package core.requests.customer;

public class ListCartItemsRequest {

    private final Long userId;

    public ListCartItemsRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
