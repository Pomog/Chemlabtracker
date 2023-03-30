package workshop.core.responses;

public class RemoveCarResponse {
    private boolean carRemoved;

    public RemoveCarResponse(boolean carRemoved) {
        this.carRemoved = carRemoved;
    }

    public boolean isCarRemoved() {
        return carRemoved;
    }
}
