package java2.eln.core.services.validators;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.DeleteReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DelReactionValidator {
    @Autowired
    DatabaseIM databaseIM;

//    public DelReactionValidator(DatabaseIM databaseIM) {
//        this.databaseIM = databaseIM;
//    }

    private Optional<CoreError> codeValidate (DeleteReactionRequest deleteReactionRequest){
        return (deleteReactionRequest.getCode() == null || deleteReactionRequest.getCode().isBlank())
                ? Optional.of(new CoreError("Reaction Code", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> reactionIsPresentValidate (DeleteReactionRequest deleteReactionRequest){
        return (databaseIM.hasReactionWithCode(deleteReactionRequest.getCode()))
                ? Optional.empty()
                : Optional.of(new CoreError("Reaction code not found", "enter the code of the reaction existing in the database"));
    }

    public List<CoreError> validate(DeleteReactionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        codeValidate(request).ifPresent(errors::add);
        reactionIsPresentValidate(request).ifPresent(errors::add);
        return errors;
    }
}
