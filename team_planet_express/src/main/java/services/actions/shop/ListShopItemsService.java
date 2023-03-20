package services.actions.shop;

import database.Database;
import domain.item.Item;

import java.util.List;

public class ListShopItemsService {

    private final Database database;

    public ListShopItemsService(Database database) {
        this.database = database;
    }

    public List<Item> execute() {
        return database.accessItemDatabase().getAllItems();
    }

}
