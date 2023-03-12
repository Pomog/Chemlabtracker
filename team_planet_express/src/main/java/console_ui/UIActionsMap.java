package console_ui;

import database.CartDatabase;
import database.CartItemDatabase;
import database.ItemDatabase;
import user_input.UserCommunication;

import java.util.HashMap;
import java.util.Map;

public class UIActionsMap {

    private final Map<Integer, UIAction> uiActionsMap;

    public UIActionsMap(ItemDatabase itemDatabase, CartDatabase cartDatabase, CartItemDatabase cartItemDatabase, UserCommunication userCommunication) {
        Map<Integer, UIAction> uiActionsMap = new HashMap<>();
        uiActionsMap.put(1, new ListShopItemsUIAction(itemDatabase, userCommunication));
        uiActionsMap.put(2, new AddItemToCartUIAction(itemDatabase, cartItemDatabase, userCommunication));
        uiActionsMap.put(3, new RemoveItemFromCartUIAction(itemDatabase, cartItemDatabase, userCommunication));
        uiActionsMap.put(4, new ListCartItemsUIAction(cartItemDatabase, userCommunication));
        uiActionsMap.put(5, new BuyUIAction(cartDatabase, userCommunication));
        uiActionsMap.put(9, new ExitUIAction(userCommunication));
        this.uiActionsMap = uiActionsMap;
    }

    public Map<Integer, UIAction> getUiActionsMap() {
        return uiActionsMap;
    }

}
