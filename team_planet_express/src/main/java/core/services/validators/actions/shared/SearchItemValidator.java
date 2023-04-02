package core.services.validators.actions.shared;

import core.requests.shared.SearchItemRequest;
import core.responses.CoreError;
import core.services.exception.InternalSystemCollapseException;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.util.ArrayList;
import java.util.List;

//TODO validate orderingRules ?
public class SearchItemValidator {

    private static final String FIELD_PRICE = "price";
    private static final String FIELD_PAGE_SIZE = "page_size";
    private static final String VALUE_NAME_PRICE = "Price";
    private static final String VALUE_NAME_PAGE_SIZE = "Page size";

    private final InputStringValidator inputStringValidator;
    private List<CoreError> errors;

    public SearchItemValidator(InputStringValidator inputStringValidator) {
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(SearchItemRequest request) {
        errors = new ArrayList<>();
        validatePrice(request.getPrice());
        if (request.getPagingRule() != null) {
            validatePageNumber(request.getPagingRule().getPageNumber());
            validatePageSize(request.getPagingRule().getPageSize());
        }
        return errors;
    }

    private void validatePrice(String price) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

    private void validatePageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber <= 0) {
            throw new InternalSystemCollapseException();
        }
    }

    private void validatePageSize(String pageSize) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(pageSize, FIELD_PAGE_SIZE, VALUE_NAME_PAGE_SIZE);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsGreaterThanZero(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotDecimal(record).ifPresent(errors::add);
    }

}
