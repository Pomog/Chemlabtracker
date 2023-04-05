package shop.core.services.validators.actions.shared;

import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.util.ArrayList;
import java.util.List;

//TODO validate orderingRules ?
public class SearchItemValidator {

    private static final String FIELD_PRICE = "price";
    private static final String FIELD_PAGE_SIZE = "page_size";
    private static final String VALUE_NAME_PRICE = "Price";
    private static final String VALUE_NAME_PAGE_SIZE = "Page size";

    private final InputStringValidator inputStringValidator;

    public SearchItemValidator(InputStringValidator inputStringValidator) {
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(SearchItemRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePrice(request.getPrice(), errors);
        if (request.getPagingRule() != null) {
            validatePageNumber(request.getPagingRule().getPageNumber());
            validatePageSize(request.getPagingRule().getPageSize(), errors);
        }
        return errors;
    }

    private void validatePrice(String price, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

    private void validatePageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber <= 0) {
            throw new InternalSystemCollapseException();
        }
    }

    private void validatePageSize(String pageSize, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(pageSize, FIELD_PAGE_SIZE, VALUE_NAME_PAGE_SIZE);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsGreaterThanZero(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotDecimal(record).ifPresent(errors::add);
    }

}
