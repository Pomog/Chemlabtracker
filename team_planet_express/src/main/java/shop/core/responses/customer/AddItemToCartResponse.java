package shop.core.responses.customer;

import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

public class AddItemToCartResponse extends CoreResponse {

    public AddItemToCartResponse() {
    }

    public AddItemToCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
