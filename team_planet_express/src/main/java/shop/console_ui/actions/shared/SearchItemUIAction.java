package shop.console_ui.actions.shared;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.console_ui.item_list.OrderingUIElement;
import shop.console_ui.item_list.PagingUIElement;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

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
        String itemName = userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String price = userCommunication.requestInput(PROMPT_TOPIC_PRICE);
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
