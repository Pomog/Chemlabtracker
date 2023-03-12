import cart.Cart;
import console_ui.UIActionsMap;
import console_ui.UIMenu;
import database.*;
import item.Item;
import item_generator.RandomItemGeneratorImpl;
import user_input.UserCommunication;

import java.util.List;

public class Shop {

    public static void main(String[] args) {

        ItemDatabase itemDatabase = new InMemoryItemDatabaseImpl();
        CartDatabase cartDatabase = new InMemoryCartDatabaseImpl();
        CartItemDatabase cartItemDatabase = new InMemoryCartItemDatabaseImpl();
        UserCommunication userCommunication = new UserCommunication();
        UIActionsMap uiActionsMap = new UIActionsMap(itemDatabase, cartDatabase, cartItemDatabase, userCommunication);
        UIMenu uiMenu = new UIMenu(uiActionsMap.getUiActionsMap(), userCommunication);

        List<Item> fakeItems = new RandomItemGeneratorImpl().createItems();
        for (Item item : fakeItems) {
            itemDatabase.save(item);
        }
        cartDatabase.save(new Cart());

        uiMenu.startUI();

    }

}
