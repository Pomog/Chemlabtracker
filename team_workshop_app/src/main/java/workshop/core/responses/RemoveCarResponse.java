package workshop.core.responses;

import workshop.CoreError;
import workshop.CoreResponse;

import java.util.List;

public class RemoveCarResponse extends CoreResponse {
    private boolean carRemoved;

    public RemoveCarResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveCarResponse(boolean carRemoved) {
        this.carRemoved = carRemoved;
    }

    public boolean isCarRemoved() {
        return carRemoved;
    }
}
