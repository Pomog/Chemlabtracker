package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.DelReactionRequest;
import java2.eln.core.responses.DelRectionResponse;

public class DelReactionService {
    private DatabaseIM databaseIM;

    public DelReactionService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }
    public DelRectionResponse execute (DelReactionRequest delReactionRequest){
        String code = delReactionRequest.getCode();
        return new DelRectionResponse(databaseIM.delReactionByCode(code));
    }
}
