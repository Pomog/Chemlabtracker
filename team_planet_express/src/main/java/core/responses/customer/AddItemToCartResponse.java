package core.responses.customer;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class AddItemToCartResponse extends CoreResponse {

    public AddItemToCartResponse() {
    }

    public AddItemToCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
