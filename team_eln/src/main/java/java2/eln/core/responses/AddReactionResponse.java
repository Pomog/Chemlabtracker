package java2.eln.core.responses;

import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.responses.errorPattern.CoreResponse;
import java2.eln.domain.ReactionData;

import java.util.List;

/**
 * A response object that encapsulates the result of adding a reaction to the system.
 */
public class AddReactionResponse extends CoreResponse {
    /**
     * The reaction data that was added to the system.
     */
    private ReactionData reactionData;

    /**
     * Creates a new AddReactionResponse object with a list of errors.
     *
     * @param errors the list of errors to include in the response
     */
    public AddReactionResponse(List<CoreError> errors) {
        super(errors);
    }

    /**
     * Creates a new AddReactionResponse object with the specified reaction data.
     *
     * @param reactionData the reaction data to include in the response
     */
    public AddReactionResponse(ReactionData reactionData) {
        this.reactionData = reactionData;
    }

    /**
     * Gets the reaction data included in the response.
     *
     * @return the reaction data
     */
    public ReactionData getReactionData() {
        return reactionData;
    }
}

