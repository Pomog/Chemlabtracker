package core.responses.customer;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class BuyResponse extends CoreResponse {

    public BuyResponse() {
    }

    public BuyResponse(List<CoreError> errors) {
        super(errors);
    }

}
