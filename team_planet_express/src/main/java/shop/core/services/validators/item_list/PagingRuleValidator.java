package shop.core.services.validators.item_list;

import shop.core.responses.CoreError;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.paging.PagingRule;

import java.util.ArrayList;
import java.util.List;

public class PagingRuleValidator {

    private static final String FIELD_PAGE_SIZE = "page_size";
    private static final String VALUE_NAME_PAGE_SIZE = "Page size";

    private final InputStringValidator inputStringValidator;

    public PagingRuleValidator(InputStringValidator inputStringValidator) {
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(PagingRule pagingRule) {
        List<CoreError> errors = new ArrayList<>();
        validatePageNumber(pagingRule.getPageNumber());
        validatePageSize(pagingRule.getPageSize(), errors);
        return errors;
    }

    private void validatePageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber <= 0) {
            throw new InternalSystemCollapseException();
        }
    }

    private void validatePageSize(String pageSize, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(pageSize, FIELD_PAGE_SIZE, VALUE_NAME_PAGE_SIZE);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        errors.addAll(inputStringValidator.validateIsNumberGreaterThanZeroNotDecimal(inputStringValidatorData));
    }

}
