package java2.eln.core.responses;

import java2.eln.core.responses.errorPattern.CoreResponse;
import java2.eln.core.domain.ReactionData;

import java.util.List;

public class GetAllReactionsResponse extends CoreResponse {
    private List<ReactionData> reactionsList;

    public GetAllReactionsResponse(List<ReactionData> reactionsList) {
        this.reactionsList = reactionsList;
    }

    public List<ReactionData> getReactionsList() {
        return reactionsList;
    }
}
