package core.responses.manager;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class ChangeItemDataResponse extends CoreResponse {

    public ChangeItemDataResponse() {
    }

    public ChangeItemDataResponse(List<CoreError> errors) {
        super(errors);
    }

}
