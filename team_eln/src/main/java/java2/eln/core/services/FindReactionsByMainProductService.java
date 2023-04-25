package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.FindReactionsByMainProductRequest;
import java2.eln.core.responses.FindReactionsByMainProductResponse;
import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;
import java2.eln.domain.ReactionData;
import java2.eln.domain.StructureData;
import org.openscience.cdk.tools.manipulator.AtomContainerComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class FindReactionsByMainProductService {

    @DIDependency
    DatabaseIM databaseIM;

//    public FindReactionsByMainProductService(DatabaseIM databaseIM) {
//        this.databaseIM = databaseIM;
//    }
    public FindReactionsByMainProductResponse execute (FindReactionsByMainProductRequest findReactionsByMainProductRequest){
        StructureData searchedSubstance = findReactionsByMainProductRequest.getSearchedSubstance();
        AtomContainerComparator comparator = new AtomContainerComparator();

        List<ReactionData> searchingResults = databaseIM.getAllReactions().stream()
                .filter(reactionData -> comparator.compare(searchedSubstance.getMol(), reactionData.getMainProduct().getMol()) == 0)
                .collect(Collectors.toList());

        return new FindReactionsByMainProductResponse(searchingResults);
    }
}
