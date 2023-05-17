package java2.eln.core.services.validators;

import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.requests.Ordering;
import java2.eln.core.responses.errorPattern.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class FindReactionValidator {

    public List<CoreError> validate(FindReactionRequest request) {
        List<CoreError> errors = new ArrayList<>();
        allFieldsAreEmpty(request).ifPresent(errors::add);

        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }

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

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("code")
                || ordering.getOrderBy().equals("name")
                || ordering.getOrderBy().equals("yield")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'code', 'name' or 'yield' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

}
