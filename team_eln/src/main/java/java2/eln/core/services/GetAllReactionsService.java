package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.domain.ReactionData;

import java.util.List;

public class GetAllReactionsService {
    private DatabaseIM databaseIM;

    public GetAllReactionsService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }
    public List<ReactionData> execute (){
        return databaseIM.getAllReactions();
    }
}
