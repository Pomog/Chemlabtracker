package core.responses.customer;

import java.util.List;

public class RemoveItemFromCartResponse extends CoreResponse {

    public RemoveItemFromCartResponse() {
    }

    public RemoveItemFromCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
