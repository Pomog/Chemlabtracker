package java2.eln.core.responses;
import java2.eln.core.responses.errorPattern.CoreResponse;
import java2.eln.core.domain.ReactionData;
import java.util.List;

public class FindReactionsByMainProductResponse extends CoreResponse {
    List<ReactionData> searchingResults;

    public FindReactionsByMainProductResponse(List<ReactionData> searchingResults) {
        this.searchingResults = searchingResults;
    }

    public List<ReactionData> getSearchingResults() {
        System.out.println("Search Results :");
        return searchingResults;
    }
}
