package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.shared.SearchItemRequest;
import core.responses.shared.SearchItemResponse;
import core.services.actions.shared.SearchItemService;
import core.support.ordering.OrderBy;
import core.support.ordering.OrderDirection;
import core.support.ordering.OrderingRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchItemUIAction extends UIAction {

    private static final String ACTION_NAME = "Search item in the shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALL_USERS);

    private static final String PROMPT_TOPIC_NAME = "full or partial item name to search for: ";
    private static final String PROMPT_TOPIC_PRICE = "maximal item price to search for: ";
    private static final String PROMPT_TOPIC_ORDER_BY_NAME = "\"Y\" if you wish to order by name: ";
    private static final String PROMPT_TOPIC_ORDER_BY_PRICE = "\"Y\" if you wish to order by price: ";
    private static final String PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION = "\"Y\" if you wish to sort in descending order: ";
    private static final String YES = "y";
    private static final String MESSAGE_NO_MATCH = "No items matched search parameters.";
    private static final String MESSAGE_SEARCH_RESULTS = "Search results:";

    private final SearchItemService searchItemService;
    private final UserCommunication userCommunication;

    public SearchItemUIAction(SearchItemService searchItemService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.searchItemService = searchItemService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String price = userCommunication.getInput();
        List<OrderingRule> orderingRules = new ArrayList<>();
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_NAME, OrderBy.NAME).ifPresent(orderingRules::add);
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_PRICE, OrderBy.PRICE).ifPresent(orderingRules::add);
        SearchItemRequest request = new SearchItemRequest(itemName, price, orderingRules);
        SearchItemResponse response = searchItemService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else if (response.getItems().isEmpty()) {
            userCommunication.informUser(MESSAGE_NO_MATCH);
        } else {
            userCommunication.informUser(MESSAGE_SEARCH_RESULTS);
            response.getItems().forEach(item -> userCommunication.informUser(item.toString()));
        }
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
