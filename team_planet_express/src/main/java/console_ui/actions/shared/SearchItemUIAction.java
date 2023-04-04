package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import console_ui.item_list.ordering.OrderingUIElement;
import console_ui.item_list.paging.PagingUIElement;
import core.domain.user.UserRole;
import core.requests.shared.SearchItemRequest;
import core.responses.shared.SearchItemResponse;
import core.services.actions.shared.SearchItemService;
import core.support.ordering.OrderingRule;
import core.support.paging.PagingRule;

import java.util.List;

public class SearchItemUIAction extends UIAction {

    private static final String ACTION_NAME = "Search item in the shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALL_USERS);

    private static final String PROMPT_TOPIC_NAME = "full or partial item name to search for: ";
    private static final String PROMPT_TOPIC_PRICE = "maximal item price to search for: ";
    private static final String MESSAGE_NO_MATCH = "No items matched search parameters.";
    private static final String MESSAGE_SEARCH_RESULTS = "Search results:";

    private final SearchItemService searchItemService;
    private final OrderingUIElement orderingUIElement;
    private final PagingUIElement pagingUIElement;
    private final UserCommunication userCommunication;

    public SearchItemUIAction(SearchItemService searchItemService, OrderingUIElement orderingUIElement, PagingUIElement pagingUIElement, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.searchItemService = searchItemService;
        this.orderingUIElement = orderingUIElement;
        this.pagingUIElement = pagingUIElement;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String price = userCommunication.getInput();
        List<OrderingRule> orderingRules = orderingUIElement.getOrderingRules();
        PagingRule pagingRule = pagingUIElement.getPagingRule();
        SearchItemRequest request = new SearchItemRequest(itemName, price, orderingRules, pagingRule);
        showResults(request);
    }

    private void showResults(SearchItemRequest request) {
        boolean continuePaging = false;
        do {
            SearchItemResponse response = searchItemService.execute(request);
            if (response.hasErrors()) {
                response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
            } else if (response.getItems().isEmpty()) {
                userCommunication.informUser(MESSAGE_NO_MATCH);
            } else {
                userCommunication.informUser(MESSAGE_SEARCH_RESULTS);
                response.getItems().forEach(item -> userCommunication.informUser(item.toString()));
                continuePaging = pagingUIElement.continuePagingThrough(request.getPagingRule(), response.getTotalFoundItemCount());
            }
        } while (continuePaging);
    }

}
