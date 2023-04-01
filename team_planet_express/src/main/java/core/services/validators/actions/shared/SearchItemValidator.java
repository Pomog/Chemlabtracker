package core.services.validators.actions.shared;

import core.requests.shared.SearchItemRequest;
import core.responses.CoreError;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.util.ArrayList;
import java.util.List;

//TODO validate orderingRules ?
public class SearchItemValidator {

    private static final String FIELD_PRICE = "price";
    private static final String VALUE_NAME_PRICE = "Price";

    private final InputStringValidator inputStringValidator;
    private List<CoreError> errors;

    public SearchItemValidator(InputStringValidator inputStringValidator) {
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(SearchItemRequest request) {
        errors = new ArrayList<>();
        validatePrice(request.getPrice());
        return errors;
    }

    private void validatePrice(String price) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

}
