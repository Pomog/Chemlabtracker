package core.requests.customer;

public class BuyRequest {

    private final Long userId;

    public BuyRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
