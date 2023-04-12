package shop.core.services.validators.actions.shared;

import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.item_list.OrderingRuleValidator;
import shop.core.services.validators.item_list.PagingRuleValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.util.ArrayList;
import java.util.List;

public class SearchItemValidator {

    private static final String FIELD_PRICE = "price";
    private static final String VALUE_NAME_PRICE = "Price";

    private final InputStringValidator inputStringValidator;
    private final OrderingRuleValidator orderingRuleValidator;
    private final PagingRuleValidator pagingRuleValidator;

    public SearchItemValidator(InputStringValidator inputStringValidator, OrderingRuleValidator orderingRuleValidator, PagingRuleValidator pagingRuleValidator) {
        this.inputStringValidator = inputStringValidator;
        this.orderingRuleValidator = orderingRuleValidator;
        this.pagingRuleValidator = pagingRuleValidator;
    }

    public List<CoreError> validate(SearchItemRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePrice(request.getPrice(), errors);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validatePrice(String price, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

    private void validateOrderingIfPresent(SearchItemRequest request, List<CoreError> errors) {
        if (request.getOrderingRules() != null) {
            request.getOrderingRules().stream()
                    .map(orderingRuleValidator::validate)
                    .filter(errorList -> !errorList.isEmpty())
                    .forEach(errors::addAll);
        }
    }

    private void validatePagingIfPresent(SearchItemRequest request, List<CoreError> errors) {
        if (request.getPagingRule() != null) {
            errors.addAll(pagingRuleValidator.validate(request.getPagingRule()));
        }
    }

}
