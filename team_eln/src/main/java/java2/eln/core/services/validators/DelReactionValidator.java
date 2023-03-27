package java2.eln.core.services.validators;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.DelReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DelReactionValidator {
    private DatabaseIM databaseIM;

    public DelReactionValidator(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }

    private Optional<CoreError> codeValidate (DelReactionRequest delReactionRequest){
        return (delReactionRequest.getCode() == null || delReactionRequest.getCode().isBlank())
                ? Optional.of(new CoreError("Reaction Code", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> reactionIsPresentValidate (DelReactionRequest delReactionRequest){
        return (databaseIM.hasReactionWithCode(delReactionRequest.getCode()))
                ? Optional.empty()
                : Optional.of(new CoreError("Reaction code not found", "enter the code of the reaction existing in the database"));
    }

    public List<CoreError> validate(DelReactionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        codeValidate(request).ifPresent(errors::add);
        reactionIsPresentValidate(request).ifPresent(errors::add);
        return errors;
    }
}
