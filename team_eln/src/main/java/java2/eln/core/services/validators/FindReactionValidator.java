package java2.eln.core.services.validators;

import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FindReactionValidator {

    public List<CoreError> validate(FindReactionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        allFieldsAreEmpty(request).ifPresent(errors::add);
        invalidOrderBy(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> allFieldsAreEmpty(FindReactionRequest findReactionRequest){
        return (!requestValid(findReactionRequest))
                ? Optional.of(new CoreError("Searching parameters", "Must not be empty!"))
                : Optional.empty();
    }

    private boolean requestValid(FindReactionRequest findReactionRequest) {
        if (findReactionRequest.getCode() != null && !findReactionRequest.getCode().trim().isEmpty()) {
            return true;
        } else if (findReactionRequest.getName() != null && !findReactionRequest.getName().trim().isEmpty()) {
            return true;
        } else if (findReactionRequest.getStartingMaterial().getSmiles() != null &&
                !Objects.equals(findReactionRequest.getStartingMaterial().getSmiles(), "C")) {
            return true;
        } else if (findReactionRequest.getYield() != 0 && findReactionRequest.getYield() != null) {
            return true;
        }
        return false;
    }

    private Optional<CoreError> invalidOrderBy(FindReactionRequest findReactionRequest) {
        String orderBy = findReactionRequest.getOrderBy();
        if (orderBy != null && !orderBy.isEmpty()) {
            if (!orderBy.equals("code") && !orderBy.equals("name") && !orderBy.equals("yield")) {
                return Optional.of(new CoreError("orderBy", "Invalid value: " + orderBy));
            }
        }
        return Optional.empty();
    }
}
