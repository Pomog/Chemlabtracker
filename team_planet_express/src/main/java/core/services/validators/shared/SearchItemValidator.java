package core.services.validators.shared;

import core.requests.shared.SearchItemRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchItemValidator {
    private static final String FIELD_PRICE = "price";
    private static final String ERROR_PRICE_NOT_NUMBER = "Error: Price should be a number.";
    private static final String ERROR_PRICE_NEGATIVE = "Error: Price should not be negative.";

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE_NUMBER = "[0-9]+(.[0-9]+)?";

    private List<CoreError> errors;


    public List<CoreError> validate(SearchItemRequest request) {
        errors = new ArrayList<>();
        validatePrice(request.getPrice());
        return errors;
    }


    private void validatePrice(String price) {
        if (validateIsPresent(price)) {
            validateIsNumber(price, FIELD_PRICE, ERROR_PRICE_NOT_NUMBER).ifPresent(errors::add);
            validateIsPositive(price, FIELD_PRICE, ERROR_PRICE_NEGATIVE).ifPresent(errors::add);
        }
    }


    private boolean validateIsPresent(String value) {
        return value != null && !value.isBlank();
    }


    //I would have made those price specific
    //But I guess it is some sort of future-proofing of whatever..
    private Optional<CoreError> validateIsNumber(String value, String field, String errorMessage) {
        return (value != null && !value.isBlank() &&
                !value.matches(REGEX_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsPositive(String value, String field, String errorMessage) {
        return (value != null && !value.isBlank() &&
                !value.matches(REGEX_NOT_NEGATIVE_NUMBER))
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }


}
