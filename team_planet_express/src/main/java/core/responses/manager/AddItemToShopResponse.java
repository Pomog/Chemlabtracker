package core.responses.manager;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class AddItemToShopResponse extends CoreResponse {

    public AddItemToShopResponse() {
    }

    public AddItemToShopResponse(List<CoreError> errors) {
        super(errors);
    }

}
