package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.responses.FindReactionResponse;
import java2.eln.domain.ReactionData;
import java2.eln.domain.StructureData;

import java.util.List;
import java.util.stream.Collectors;

public class FindReactionService {
    private final DatabaseIM databaseIM;

    public FindReactionService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }

    public FindReactionResponse execute (FindReactionRequest findReactionRequest) {
        List<ReactionData> searchingResults = databaseIM.getAllReactions().stream()
            .filter(reaction -> compareObjects(findReactionRequest, reaction))
            .collect(Collectors.toList());
        return new FindReactionResponse(searchingResults);
    }

    private boolean compareObjects(FindReactionRequest findReactionRequest, ReactionData reaction) {
        boolean codeMatch = matchSearchCriteria(findReactionRequest.getCode(), reaction.getCode());
        boolean nameMatch = matchSearchCriteria(findReactionRequest.getName(), reaction.getName());
      //  boolean yieldMatch = matchSearchCriteria(reaction.getReactionYield(), findReactionRequest.getYield());
        boolean startingMaterialMatch = matchSearchCriteria(findReactionRequest.getStartingMaterial(), reaction.getStartingMaterials());
        // return true only if all search criteria match
        return codeMatch && nameMatch && startingMaterialMatch;
    }

    private boolean matchSearchCriteria (Object searchCriteria, Object reactionProperty) {
        boolean verdict = false;
        if (searchCriteria == null) {
            verdict = true; // match all values if search criteria is null

            } else if (searchCriteria instanceof String) {
                String strSearchCriteria = ((String) searchCriteria).trim();
                String strBookProperty = ((String) reactionProperty).toLowerCase();
            verdict = strSearchCriteria.isEmpty() || strBookProperty.contains(strSearchCriteria.toLowerCase());

            } else if (reactionProperty instanceof List) {
            verdict = ((List<?>) reactionProperty).contains(searchCriteria) ||
                    searchCriteria.equals(new StructureData("C"));

            }
            else {
            verdict = searchCriteria.equals(reactionProperty);
        }
        return verdict;
    }
}
