package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.requests.Ordering;
import java2.eln.core.responses.FindReactionResponse;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.services.validators.FindReactionValidator;
import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;
import java2.eln.domain.ReactionData;
import java2.eln.domain.StructureData;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class FindReactionService {

    @DIDependency
    DatabaseIM databaseIM;

    @DIDependency
    FindReactionValidator findReactionValidator;

//    public FindReactionService(DatabaseIM databaseIM, FindReactionValidator findReactionValidator) {
//        this.databaseIM = databaseIM;
//        this.findReactionValidator = findReactionValidator;
//    }

    public FindReactionResponse execute (FindReactionRequest findReactionRequest) {
        List<CoreError> errors = findReactionValidator.validate(findReactionRequest);
        if (!errors.isEmpty()) {
            return new FindReactionResponse(errors, true);
        }

        List<ReactionData> searchingResults = getSearchingResults(findReactionRequest);

        searchingResults = order(searchingResults, findReactionRequest.getOrdering());

        return new FindReactionResponse(searchingResults);
    }

    @NotNull
    private List<ReactionData> getSearchingResults(FindReactionRequest findReactionRequest) {
        return databaseIM.getAllReactions().stream()
            .filter(reaction -> compareObjects(findReactionRequest, reaction))
            .collect(Collectors.toList());
    }

    private List<ReactionData> order(List<ReactionData> reactions, Ordering ordering) {
        if (ordering != null && ordering.getOrderBy() != null) {
            Comparator<ReactionData> comparator;
            if (ordering.getOrderBy().equals("code")) {
                comparator = Comparator.comparing(ReactionData::getCode);
            } else if (ordering.getOrderBy().equals("yield")) {
                comparator = Comparator.comparing(ReactionData::getReactionYield);
            } else {
                comparator = Comparator.comparing(ReactionData::getName);
            }
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return reactions.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            // Return the same list if the ordering parameter is null or orderBy is not specified
            return reactions;
        }
    }


    private boolean compareObjects(FindReactionRequest findReactionRequest, ReactionData reaction) {
        boolean codeMatch = matchSearchCriteria(findReactionRequest.getCode(), reaction.getCode());
        boolean nameMatch = matchSearchCriteria(findReactionRequest.getName(), reaction.getName());
        boolean yieldMatch = matchSearchCriteria(findReactionRequest.getYield(), reaction.getReactionYield());
        boolean startingMaterialMatch = matchSearchCriteria(findReactionRequest.getStartingMaterial(), reaction.getStartingMaterials());
        // return true only if all search criteria match
        return codeMatch && nameMatch && yieldMatch && startingMaterialMatch;
    }

    private boolean matchSearchCriteria (Object searchCriteria, Object reactionProperty) {
        boolean verdict = false;
        if (searchCriteria == null) {
            verdict = true; // match all values if search criteria is null
        } else if (searchCriteria instanceof String) {
                String strSearchCriteria = ((String) searchCriteria).trim().toLowerCase();
                String strProperty = ((String) reactionProperty).toLowerCase();
            verdict = strSearchCriteria.isEmpty() || strProperty.contains(strSearchCriteria.toLowerCase());
            }
        else if (reactionProperty instanceof List) {
            verdict = ((List<?>) reactionProperty).contains(searchCriteria) ||
                    searchCriteria.equals(new StructureData("C"));
            }
        else if (searchCriteria instanceof Double doubleSearchCriteria) {
            verdict = doubleSearchCriteria == 0 || doubleSearchCriteria.equals((Double) reactionProperty);
           }
        else {
            verdict = searchCriteria.equals(reactionProperty);
        }
        return verdict;
    }
}
