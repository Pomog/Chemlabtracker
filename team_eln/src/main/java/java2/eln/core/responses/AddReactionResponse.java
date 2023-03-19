package java2.eln.core.responses;

import java2.eln.domain.ReactionData;

import java.util.List;

public class AddReactionResponse extends CoreResponse {
    private ReactionData reactionData;

    public AddReactionResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddReactionResponse(ReactionData reactionData) {
        this.reactionData = reactionData;
    }

    public ReactionData getReactionData() {
        return reactionData;
    }
}
