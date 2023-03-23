package core.responses.customer;

import java.util.List;

public class BuyResponse extends CoreResponse {

    public BuyResponse() {
    }

    public BuyResponse(List<CoreError> errors) {
        super(errors);
    }

}
