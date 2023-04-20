package shop.core.services.validators.item_list;

import shop.core.responses.CoreError;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;
import shop.core.support.paging.PagingRule;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class PagingRuleValidator {

    private static final String FIELD_PAGE_SIZE = "page_size";
    private static final String VALUE_NAME_PAGE_SIZE = "Page size";

    @DIDependency
    private InputStringValidator inputStringValidator;

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
        InputStringValidatorRecord record = new InputStringValidatorRecord(pageSize, FIELD_PAGE_SIZE, VALUE_NAME_PAGE_SIZE);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsGreaterThanZero(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotDecimal(record).ifPresent(errors::add);
    }

}
