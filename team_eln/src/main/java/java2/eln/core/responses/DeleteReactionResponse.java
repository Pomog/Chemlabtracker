package java2.eln.core.responses;

import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.responses.errorPattern.CoreResponse;

import java.util.List;

public class DeleteReactionResponse extends CoreResponse {
    private boolean delResult;

    public boolean getDelResult() {
        return delResult;
    }

    public DeleteReactionResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteReactionResponse(boolean delResult) {
        this.delResult = delResult;
    }
}
