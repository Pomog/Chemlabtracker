package shop.core.services.validators.item_list;

import shop.core.responses.CoreError;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;

import java.util.ArrayList;
import java.util.List;

public class OrderingRuleValidator {

    public List<CoreError> validate(OrderingRule orderingRule) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(orderingRule.getOrderBy());
        validateOrderingDirection(orderingRule.getOrderDirection());
        return errors;
    }

    private void validateOrderBy(OrderBy orderBy) {
        if (orderBy == null) {
            throw new InternalSystemCollapseException();
        }
    }

    private void validateOrderingDirection(OrderDirection orderDirection) {
        if (orderDirection == null) {
            throw new InternalSystemCollapseException();
        }
    }

}
