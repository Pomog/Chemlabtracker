package java2.eln.core.services;

import java2.eln.core.requests.AddReactionRequest;
import java2.eln.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddReactionValidator {

    private Optional<CoreError> codeValidate (AddReactionRequest addReactionRequest){
        return (addReactionRequest.getCode() == null || addReactionRequest.getCode().isEmpty())
                ? Optional.of(new CoreError("Reaction Code", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> nameValidate (AddReactionRequest addReactionRequest){
        return (addReactionRequest.getName() == null || addReactionRequest.getName().isEmpty())
                ? Optional.of(new CoreError("Reaction Name", "Must not be empty!"))
                : Optional.empty();
    }

    public List<CoreError> validate(AddReactionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        codeValidate(request).ifPresent(errors::add);
        nameValidate(request).ifPresent(errors::add);
        return errors;
    }
}
