package java2.eln.services;

import java2.eln.domain.ReactionData;
import java2.eln.database.DatabaseIM;

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
