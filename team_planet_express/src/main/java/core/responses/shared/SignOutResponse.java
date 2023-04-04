package core.responses.shared;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class SignOutResponse extends CoreResponse {

    public SignOutResponse() {
    }

    public SignOutResponse(List<CoreError> errors) {
        super(errors);
    }

}
