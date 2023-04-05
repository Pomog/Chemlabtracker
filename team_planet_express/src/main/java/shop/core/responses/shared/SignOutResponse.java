package shop.core.responses.shared;

import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

public class SignOutResponse extends CoreResponse {

    public SignOutResponse() {
    }

    public SignOutResponse(List<CoreError> errors) {
        super(errors);
    }

}
