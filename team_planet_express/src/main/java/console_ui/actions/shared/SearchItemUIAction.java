package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.shared.SearchItemRequest;
import core.responses.shared.SearchItemResponse;
import core.services.actions.shared.SearchItemService;

public class SearchItemUIAction extends UIAction {

    private static final String ACTION_NAME = "Search item in the shop";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALL_USERS);

    private static final String PROMPT_TOPIC_ITEM = "item name for search: ";
    private static final String PROMPT_TOPIC_PRICE = "item price for search: ";
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
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String price = userCommunication.getInput();
        SearchItemRequest request = new SearchItemRequest(itemName, price);
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

}
