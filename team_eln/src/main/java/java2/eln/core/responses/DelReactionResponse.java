package java2.eln.core.responses;

import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.responses.errorPattern.CoreResponse;

import java.util.List;

public class DelReactionResponse extends CoreResponse {
    private boolean delResult;

    public boolean getDelResult() {
        return delResult;
    }

    public DelReactionResponse(List<CoreError> errors) {
        super(errors);
    }

    public DelReactionResponse(boolean delResult) {
        this.delResult = delResult;
    }
}
