package console_ui;

import database.ItemDatabase;
import user_input.UserCommunication;

public class ListShopItemsUIAction implements UIAction {

    private static final String ACTION_NAME = "List items";

    private static final String HEADER_TEXT = "Shop items:";

    private final ItemDatabase itemDatabase;
    private final UserCommunication userCommunication;

    public ListShopItemsUIAction(ItemDatabase itemDatabase, UserCommunication userCommunication) {
        this.itemDatabase = itemDatabase;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        itemDatabase.getAllItems().forEach(item -> userCommunication.informUser(item.toString()));
    }

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

}
