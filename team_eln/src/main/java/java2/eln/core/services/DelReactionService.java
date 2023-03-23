package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.DelReactionRequest;
import java2.eln.core.responses.AddReactionResponse;
import java2.eln.core.responses.CoreError;
import java2.eln.core.responses.DelReactionResponse;

import java.util.List;

public class DelReactionService {
    private DatabaseIM databaseIM;
    private DelReactionValidator delReactionValidator;

    public DelReactionService(DatabaseIM databaseIM, DelReactionValidator delReactionValidator) {
        this.databaseIM = databaseIM;
        this.delReactionValidator = delReactionValidator;
    }

    public DelReactionResponse execute (DelReactionRequest delReactionRequest){
        List<CoreError> errors = delReactionValidator.validate(delReactionRequest);
        if (!errors.isEmpty()) {
            return new DelReactionResponse(errors);
        }
        String code = delReactionRequest.getCode();
        return new DelReactionResponse(databaseIM.delReactionByCode(code));
    }
}
