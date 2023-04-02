package java2.eln.core.services.validators;

import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchReactionValidator {
    private Optional<CoreError> allFieldsAreEmpty(FindReactionRequest findReactionRequest){
        return (!requestValid(findReactionRequest))
                ? Optional.of(new CoreError("Search request", "Must not be empty!"))
                : Optional.empty();
    }

    public List<CoreError> validate(FindReactionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        allFieldsAreEmpty(request).ifPresent(errors::add);
        return errors;
    }

    private boolean requestValid(FindReactionRequest findReactionRequest) {
        if (findReactionRequest.getCode() != null && !findReactionRequest.getCode().trim().isEmpty()) {
            return true;
        } else if (findReactionRequest.getName() != null && !findReactionRequest.getName().trim().isEmpty()) {
            return true;
        } else if (findReactionRequest.getStartingMaterial().getSmiles() != null) {
            return true;
        } else if (findReactionRequest.getYield() != 0) {
            return true;
        }
        return false;
    }
}
