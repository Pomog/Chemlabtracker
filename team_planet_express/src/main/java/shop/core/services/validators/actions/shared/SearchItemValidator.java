package shop.core.services.validators.actions.shared;

import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.item_list.OrderingRuleValidator;
import shop.core.services.validators.item_list.PagingRuleValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO validate orderingRules ?
public class SearchItemValidator {

    private static final String FIELD_PRICE = "price";
    private static final String VALUE_NAME_PRICE = "Price";

    private final InputStringValidator inputStringValidator;
    private final PagingRuleValidator pagingRuleValidator;
    private final OrderingRuleValidator orderingRuleValidator;

    public SearchItemValidator(InputStringValidator inputStringValidator,
                               PagingRuleValidator pagingRuleValidator,
                               OrderingRuleValidator orderingRuleValidator
                               ) {
        this.inputStringValidator = inputStringValidator;
        this.pagingRuleValidator = pagingRuleValidator;
        this.orderingRuleValidator = orderingRuleValidator;
    }

    public List<CoreError> validate(SearchItemRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePrice(request.getPrice(), errors);
        validatePagingIfPresent(request, errors);
        validateOrderingIsPresent(request, errors);
        return errors;
    }

    private void validatePrice(String price, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateIsNumber(record).ifPresent(errors::add);
        inputStringValidator.validateIsNotNegative(record).ifPresent(errors::add);
    }

    private void validatePagingIfPresent(SearchItemRequest request, List<CoreError> errors) {
        if (request.getPagingRule() != null) {
            errors.addAll(pagingRuleValidator.validate(request.getPagingRule()));
        }
    }
    private void validateOrderingIsPresent(SearchItemRequest request, List<CoreError> errors) {
        if (request.getOrderingRules() != null) {
            request.getOrderingRules().stream()
                    .map(orderingRuleValidator::validate)
                    .filter(listErrors -> !listErrors.isEmpty())
                    .forEach(errors::addAll);
        }
    }
}
