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
import core.support.paging.PageNavigation;
import core.support.paging.PagingRule;

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
    private static final String PROMPT_TOPIC_PAGE_SIZE = "number of items to be displayed per page: ";
    private static final String PROMPT_PAGE_NAVIGATION = "to navigate paged results: ";
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
        PagingRule pagingRule = getPagingRule();
        //TODO lol console
        if (pagingRule != null) {
            boolean endAction = false;
            while (!endAction) {
                SearchItemRequest request = new SearchItemRequest(itemName, price, orderingRules, pagingRule);
                SearchItemResponse response = searchItemService.execute(request);
                showResults(response);
                if (!response.hasErrors()) {
                    if (Integer.parseInt(pagingRule.getPageSize()) > response.getTotalFoundItemCount()) {
                        endAction = true;
                    } else {
                        String pageNavigationOptions = getPageNavigationOptions(pagingRule.getPageNumber(), Integer.parseInt(pagingRule.getPageSize()), response.getTotalFoundItemCount());
                        boolean userInputIsValid;
                        do {
                            userCommunication.requestInput(pageNavigationOptions + PROMPT_PAGE_NAVIGATION);
                            String userInput = userCommunication.getInput();
                            userInputIsValid = true;
                            if (PageNavigation.NEXT.getText().equalsIgnoreCase(userInput) &&
                                    pageNavigationOptions.contains(PageNavigation.NEXT.getText())) {
                                pagingRule.nextPage();
                            } else if (PageNavigation.BACK.getText().equalsIgnoreCase(userInput) &&
                                    pageNavigationOptions.contains(PageNavigation.BACK.getText())) {
                                pagingRule.previousPage();
                            } else if (PageNavigation.EXIT.getText().equalsIgnoreCase(userInput)) {
                                endAction = true;
                            } else {
                                userInputIsValid = false;
                            }
                        } while (!userInputIsValid);
                    }
                } else {
                    endAction = true;
                }
            }
        } else {
            SearchItemRequest request = new SearchItemRequest(itemName, price, orderingRules, null);
            SearchItemResponse response = searchItemService.execute(request);
            showResults(response);
        }
    }

    private Optional<OrderingRule> getOrderingRule(String promptOrderBy, OrderBy orderBy) {
        userCommunication.requestInput(promptOrderBy);
        return (userCommunication.getInput().strip().equalsIgnoreCase(YES))
                ? getOrderingRuleWithDirection(orderBy)
                : Optional.empty();
    }

    private PagingRule getPagingRule() {
        userCommunication.requestInput(PROMPT_TOPIC_PAGE_SIZE);
        String pageSize = userCommunication.getInput();
        return (pageSize == null || pageSize.isBlank())
                ? null
                : new PagingRule(1, pageSize);
    }

    private void showResults(SearchItemResponse response) {
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else if (response.getItems().isEmpty()) {
            userCommunication.informUser(MESSAGE_NO_MATCH);
        } else {
            userCommunication.informUser(MESSAGE_SEARCH_RESULTS);
            response.getItems().forEach(item -> userCommunication.informUser(item.toString()));
        }
    }

    private String getPageNavigationOptions(Integer pageNumber, Integer pageSize, Integer totalFoundItemCount) {
        StringBuilder promptOptions = new StringBuilder();
        if (pageNumber > 1) {
            promptOptions.append(PageNavigation.BACK.getText()).append(", ");
        }
        if (totalFoundItemCount > pageNumber * pageSize) {
            promptOptions.append(PageNavigation.NEXT.getText()).append(", ");
        }
        promptOptions.append(PageNavigation.EXIT.getText()).append(" ");
        return promptOptions.toString();
    }

    private Optional<OrderingRule> getOrderingRuleWithDirection(OrderBy orderBy) {
        userCommunication.requestInput(PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION);
        return (userCommunication.getInput().strip().equalsIgnoreCase(YES))
                ? Optional.of(new OrderingRule(orderBy, OrderDirection.DESCENDING))
                : Optional.of(new OrderingRule(orderBy, OrderDirection.ASCENDING));
    }

}
