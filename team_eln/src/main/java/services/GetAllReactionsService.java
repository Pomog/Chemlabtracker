package services;

import domain.ReactionData;
import database.DatabaseIM;

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
