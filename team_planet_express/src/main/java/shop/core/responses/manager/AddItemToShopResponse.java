package shop.core.responses.manager;

import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

public class AddItemToShopResponse extends CoreResponse {

    public AddItemToShopResponse() {
    }

    public AddItemToShopResponse(List<CoreError> errors) {
        super(errors);
    }

}
