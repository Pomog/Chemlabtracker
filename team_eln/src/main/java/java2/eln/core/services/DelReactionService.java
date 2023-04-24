package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.DeleteReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.responses.DeleteReactionResponse;
import java2.eln.core.services.validators.DelReactionValidator;
import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class DelReactionService {

    @DIDependency
    DatabaseIM databaseIM;

    @DIDependency
    DelReactionValidator delReactionValidator;

//    public DelReactionService(DatabaseIM databaseIM, DelReactionValidator delReactionValidator) {
//        this.databaseIM = databaseIM;
//        this.delReactionValidator = delReactionValidator;
//    }

    public DeleteReactionResponse execute (DeleteReactionRequest deleteReactionRequest){
        List<CoreError> errors = delReactionValidator.validate(deleteReactionRequest);
        if (!errors.isEmpty()) {
            return new DeleteReactionResponse(errors);
        }
        String code = deleteReactionRequest.getCode();
        return new DeleteReactionResponse(databaseIM.delReactionByCode(code));
    }
}
