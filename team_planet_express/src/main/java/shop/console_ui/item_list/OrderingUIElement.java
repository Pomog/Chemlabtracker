package shop.console_ui.item_list;

import shop.console_ui.UserCommunication;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class OrderingUIElement {

    private static final String PROMPT_TOPIC_ORDER_BY_NAME = "\"Y\" if you wish to order by name: ";
    private static final String PROMPT_TOPIC_ORDER_BY_PRICE = "\"Y\" if you wish to order by price: ";
    private static final String PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION = "\"Y\" if you wish to sort in descending order: ";
    private static final String YES = "y";

    @DIDependency
    private UserCommunication userCommunication;


    public List<OrderingRule> getOrderingRules() {
        List<OrderingRule> orderingRules = new ArrayList<>();
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_NAME, OrderBy.NAME).ifPresent(orderingRules::add);
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_PRICE, OrderBy.PRICE).ifPresent(orderingRules::add);
        return orderingRules;
    }

    private Optional<OrderingRule> getOrderingRule(String promptOrderBy, OrderBy orderBy) {
        return (userCommunication.requestInput(promptOrderBy)
                .strip().equalsIgnoreCase(YES))
                ? getOrderingRuleWithDirection(orderBy)
                : Optional.empty();
    }

    private Optional<OrderingRule> getOrderingRuleWithDirection(OrderBy orderBy) {
        return (userCommunication.requestInput(PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION)
                .strip().equalsIgnoreCase(YES))
                ? Optional.of(new OrderingRule(orderBy, OrderDirection.DESCENDING))
                : Optional.of(new OrderingRule(orderBy, OrderDirection.ASCENDING));
    }

}
