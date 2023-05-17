package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.FindReactionsByMainProductRequest;
import java2.eln.core.responses.FindReactionsByMainProductResponse;
import java2.eln.core.domain.ReactionData;
import java2.eln.core.domain.StructureData;
import org.openscience.cdk.tools.manipulator.AtomContainerComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindReactionsByMainProductService {

    @Autowired
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
