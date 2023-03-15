package java2.eln.services;

import java2.eln.database.DatabaseIM;

public class DelReactionService {
    private DatabaseIM databaseIM;

    public DelReactionService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }
    public void execute (String code){
        databaseIM.delReactionByCode(code);
    }
}
