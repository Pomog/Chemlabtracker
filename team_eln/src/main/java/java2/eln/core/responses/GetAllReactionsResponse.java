package java2.eln.core.responses;

import java2.eln.domain.ReactionData;

import java.util.List;

public class GetAllReactionsResponse {
    private List<ReactionData> reactionsList;

    public GetAllReactionsResponse(List<ReactionData> reactionsList) {
        this.reactionsList = reactionsList;
    }

    public List<ReactionData> getReactionsList() {
        return reactionsList;
    }
}
