package console_ui.actions;

import console_ui.UserCommunication;
import domain.item.Item;
import domain.user.UserRole;
import services.actions.ListShopItemsService;

import java.util.List;

public class ListShopItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALLUSERS);

    private static final String HEADER_TEXT = "Shop items:";

    private final ListShopItemsService listShopItemsService;
    private final UserCommunication userCommunication;

    public ListShopItemsUIAction(ListShopItemsService listShopItemsService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.listShopItemsService = listShopItemsService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        List<Item> items = listShopItemsService.execute();
        items.forEach(item -> userCommunication.informUser(item.toString()));
    }

}
