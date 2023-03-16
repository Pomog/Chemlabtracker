package java2.eln.core.responses;

import java2.eln.domain.ReactionData;

public class AddReactionResponse {
    private ReactionData reactionData;

    public AddReactionResponse(ReactionData reactionData) {
        this.reactionData = reactionData;
    }

    public ReactionData getReactionData() {
        return reactionData;
    }
}
