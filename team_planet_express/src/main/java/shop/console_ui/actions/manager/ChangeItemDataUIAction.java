package shop.console_ui.actions.manager;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.services.actions.manager.ChangeItemDataService;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class ChangeItemDataUIAction extends UIAction {

    private static final String ACTION_NAME = "Change existing item data";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.MANAGER);

    private static final String PROMPT_TOPIC_ID = "id of the item you wish to change: ";
    private static final String PROMPT_TOPIC_NAME = "new name: ";
    private static final String PROMPT_TOPIC_PRICE = "new price: ";
    private static final String PROMPT_TOPIC_QUANTITY = "new available quantity: ";
    private static final String MESSAGE_INPUT_RULES = "Leave any field blank to keep current value.";
    private static final String MESSAGE_ITEM_CHANGED = "Item data changed.";

    @DIDependency
    private ChangeItemDataService changeItemDataService;
    @DIDependency
    private UserCommunication userCommunication;

    public ChangeItemDataUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        String itemId = userCommunication.requestInput(PROMPT_TOPIC_ID);
        userCommunication.informUser(MESSAGE_INPUT_RULES);
        String newItemName = userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String newPrice = userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String newAvailableQuantity = userCommunication.requestInput(PROMPT_TOPIC_QUANTITY);
        ChangeItemDataRequest request = new ChangeItemDataRequest(itemId, newItemName, newPrice, newAvailableQuantity);
        ChangeItemDataResponse response = changeItemDataService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_ITEM_CHANGED);

        }
    }

}
