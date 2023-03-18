package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.FindReactionsByMainProductRequest;
import java2.eln.core.responses.FindReactionsByMainProductResponse;
import java2.eln.domain.ReactionData;
import java2.eln.domain.StructureData;
import org.openscience.cdk.tools.manipulator.AtomContainerComparator;

import java.util.ArrayList;
import java.util.List;

public class FindReactionsByMainProductService {
    private DatabaseIM databaseIM;

    public FindReactionsByMainProductService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }
    public FindReactionsByMainProductResponse execute (FindReactionsByMainProductRequest findReactionsByMainProductRequest){
        StructureData searchedSubstance = findReactionsByMainProductRequest.getSearchedSubstance();
        List<ReactionData> searchingResults = new ArrayList<>();
        AtomContainerComparator comparator = new AtomContainerComparator();

        for (ReactionData reactionData : databaseIM.getAllReactions()) {
            int comparatorResult =
                    comparator.compare(searchedSubstance.getMol(), reactionData.getMainProduct().getMol());
            if (comparatorResult == 0){
                searchingResults.add(reactionData);
            }
        }

        return new FindReactionsByMainProductResponse(searchingResults);
    }
}
