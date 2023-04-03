package core.services.actions.customer;

import core.database.Database;
import core.requests.customer.ListShopItemsRequest;
import core.responses.customer.ListShopItemsResponse;

public class ListShopItemsService {

    private final Database database;

    public ListShopItemsService(Database database) {
        this.database = database;
    }

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(database.accessItemDatabase().getAllItems());
    }

}
