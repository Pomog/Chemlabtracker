package core.services.actions.shared;

import core.database.Database;
import core.domain.item.Item;
import core.requests.shared.SearchItemRequest;
import core.responses.CoreError;
import core.responses.shared.SearchItemResponse;
import core.services.validators.actions.shared.SearchItemValidator;
import core.support.ordering.OrderBy;
import core.support.ordering.OrderDirection;
import core.support.ordering.OrderingRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SearchItemService {

    private final Database database;
    private final SearchItemValidator validator;

    public SearchItemService(Database database, SearchItemValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(null, errors);
        } else {
            List<Item> items = search(request);
            items = order(items, request.getOrderingRules());
            response = new SearchItemResponse(items, null);
        }
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;
        //TODO is blank actually ok in here ?
        if (request.getItemName() != null && !isPresent(request.getPrice())) {
            items = database.accessItemDatabase().searchByName(request.getItemName());
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = database.accessItemDatabase().searchByNameAndPrice(request.getItemName(), price);
        } else {
            items = database.accessItemDatabase().getAllItems();
        }
        return items;
    }

    //TODO move it somewhere ?
    private List<Item> order(List<Item> items, List<OrderingRule> orderingRules) {
        if (orderingRules != null && orderingRules.size() > 0) {
            items = orderByPrice(items, orderingRules);
            items = orderByName(items, orderingRules);
        }
        return items;
    }

    private boolean isPresent(String value) {
        return value != null && !value.isBlank();
    }

    private List<Item> orderByPrice(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForPrice = getOrderingRule(orderingRules, OrderBy.PRICE);
        return orderWithDirection(items, orderingRuleForPrice, Comparator.comparing(Item::getPrice));
    }

    private List<Item> orderByName(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForName = getOrderingRule(orderingRules, OrderBy.NAME);
        return orderWithDirection(items, orderingRuleForName, Comparator.comparing(Item::getName));
    }

    private Optional<OrderingRule> getOrderingRule(List<OrderingRule> orderingRules, OrderBy orderBy) {
        return orderingRules.stream()
                .filter(orderingRule -> orderBy.equals(orderingRule.getOrderBy()))
                .findFirst();
    }

    private List<Item> orderWithDirection(List<Item> items, Optional<OrderingRule> orderingRule, Comparator<Item> comparator) {
        return orderingRule.map(rule -> items.stream()
                .sorted(rule.getOrderDirection().equals(OrderDirection.DESCENDING)
                        ? comparator.reversed()
                        : comparator)
                .toList()).orElse(items);
    }

}
