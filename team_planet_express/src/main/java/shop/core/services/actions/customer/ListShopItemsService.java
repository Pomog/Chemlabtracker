package shop.core.services.actions.customer;

import shop.core.database.Database;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class ListShopItemsService {

    @DIDependency
    private Database database;


    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(database.accessItemDatabase().getAllItems());
    }

}
