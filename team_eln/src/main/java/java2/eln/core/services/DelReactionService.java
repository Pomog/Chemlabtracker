package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;

public class DelReactionService {
    private DatabaseIM databaseIM;

    public DelReactionService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }
    public void execute (String code){
        databaseIM.delReactionByCode(code);
    }
}
