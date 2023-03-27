package java2.eln.core.responses;

import java2.eln.core.responses.errorPattern.CoreResponse;
import java2.eln.domain.ReactionData;

import java.util.List;

public class FindReactionResponse extends CoreResponse {

    List<ReactionData> searchingResults;

    public FindReactionResponse(List<ReactionData> searchingResults) {
        this.searchingResults = searchingResults;
    }

    public List<ReactionData> getSearchingResults() {
        return searchingResults;
    }
}
