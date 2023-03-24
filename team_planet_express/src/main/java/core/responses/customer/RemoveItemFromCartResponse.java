package core.responses.customer;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class RemoveItemFromCartResponse extends CoreResponse {

    public RemoveItemFromCartResponse() {
    }

    public RemoveItemFromCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
