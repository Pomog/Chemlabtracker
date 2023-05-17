package java2.eln.core.responses;

import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.responses.errorPattern.CoreResponse;
import java2.eln.core.domain.ReactionData;

import java.util.List;

public class FindReactionResponse extends CoreResponse {

    private List<ReactionData> searchingResults;
    private boolean hasErrors;

    public FindReactionResponse(List<CoreError> errors, boolean hasErrors) {
        super(errors);
        this.hasErrors = hasErrors;
    }

    public FindReactionResponse(List<ReactionData> searchingResults) {
        this.searchingResults = searchingResults;
    }

    public List<ReactionData> getSearchingResults() {
        return searchingResults;
    }
}
