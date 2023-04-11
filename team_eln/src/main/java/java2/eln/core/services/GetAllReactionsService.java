package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.GetAllReactionsRequest;
import java2.eln.core.responses.GetAllReactionsResponse;
import java2.eln.domain.ReactionData;

import java.util.List;

public class GetAllReactionsService {
    private final DatabaseIM databaseIM;

    public GetAllReactionsService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }
    public GetAllReactionsResponse execute (GetAllReactionsRequest getAllReactionsRequest){
        List<ReactionData> reactionsList = databaseIM.getAllReactions();
        return new GetAllReactionsResponse(reactionsList);
    }
}
