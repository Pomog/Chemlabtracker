package console_ui.actions.manager;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.manager.ChangeItemDataResponse;
import core.services.actions.manager.ChangeItemDataService;

public class ChangeItemDataUIAction extends UIAction {

    private static final String ACTION_NAME = "Change existing item data";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.MANAGER);

    private static final String PROMPT_TOPIC_ID = "id of the item you wish to change: ";
    private static final String PROMPT_TOPIC_NAME = "new name: ";
    private static final String PROMPT_TOPIC_PRICE = "new price: ";
    private static final String PROMPT_TOPIC_QUANTITY = "new available quantity: ";
    private static final String MESSAGE_INPUT_RULES = "Leave any field blank to keep current value.";
    private static final String MESSAGE_ITEM_CHANGED = "Item data changed.";

    private final ChangeItemDataService changeItemDataService;
    private final UserCommunication userCommunication;

    public ChangeItemDataUIAction(ChangeItemDataService changeItemDataService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.changeItemDataService = changeItemDataService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ID);
        String itemId = userCommunication.getInput();
        userCommunication.informUser(MESSAGE_INPUT_RULES);
        userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String newItemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String newPrice = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
        String newAvailableQuantity = userCommunication.getInput();
        ChangeItemDataRequest request = new ChangeItemDataRequest(itemId, newItemName, newPrice, newAvailableQuantity);
        ChangeItemDataResponse response = changeItemDataService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_ITEM_CHANGED);

        }
    }

}
