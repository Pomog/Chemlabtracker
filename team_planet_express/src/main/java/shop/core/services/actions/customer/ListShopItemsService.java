package shop.core.services.actions.customer;

import shop.core.database.Database;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;

public class ListShopItemsService {

    private final Database database;

    public ListShopItemsService(Database database) {
        this.database = database;
    }

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(database.accessItemDatabase().getAllItems());
    }

}
