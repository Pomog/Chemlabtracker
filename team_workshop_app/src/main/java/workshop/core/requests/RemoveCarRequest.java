package workshop.core.requests;

public class RemoveCarRequest {
    private  Long carIdToRemove;

    public RemoveCarRequest(Long carIdToRemove) {
        this.carIdToRemove = carIdToRemove;
    }

    public Long getCarIdToRemove() {
        return carIdToRemove;
    }
}
