package console_ui.item.ordering;

import console_ui.UserCommunication;
import core.support.ordering.OrderBy;
import core.support.ordering.OrderDirection;
import core.support.ordering.OrderingRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderingUIElement {

    private static final String PROMPT_TOPIC_ORDER_BY_NAME = "\"Y\" if you wish to order by name: ";
    private static final String PROMPT_TOPIC_ORDER_BY_PRICE = "\"Y\" if you wish to order by price: ";
    private static final String PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION = "\"Y\" if you wish to sort in descending order: ";
    private static final String YES = "y";

    private final UserCommunication userCommunication;

    public OrderingUIElement(UserCommunication userCommunication) {
        this.userCommunication = userCommunication;
    }

    public List<OrderingRule> getOrderingRules() {
        List<OrderingRule> orderingRules = new ArrayList<>();
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_NAME, OrderBy.NAME).ifPresent(orderingRules::add);
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_PRICE, OrderBy.PRICE).ifPresent(orderingRules::add);
        return orderingRules;
    }

    private Optional<OrderingRule> getOrderingRule(String promptOrderBy, OrderBy orderBy) {
        userCommunication.requestInput(promptOrderBy);
        return (userCommunication.getInput().strip().equalsIgnoreCase(YES))
                ? getOrderingRuleWithDirection(orderBy)
                : Optional.empty();
    }

    private Optional<OrderingRule> getOrderingRuleWithDirection(OrderBy orderBy) {
        userCommunication.requestInput(PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION);
        return (userCommunication.getInput().strip().equalsIgnoreCase(YES))
                ? Optional.of(new OrderingRule(orderBy, OrderDirection.DESCENDING))
                : Optional.of(new OrderingRule(orderBy, OrderDirection.ASCENDING));
    }

}
