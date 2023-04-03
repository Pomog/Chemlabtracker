package core.services.actions.customer;

import core.database.Database;
import core.requests.customer.ListShopItemsRequest;
import core.responses.customer.ListShopItemsResponse;
import core.services.fake.FakeDatabaseInitializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ListShopItemsServiceTest {

    private final Database fakeDatabase = new Database();
    private final ListShopItemsRequest mockRequest = mock(ListShopItemsRequest.class);

    private final ListShopItemsService service =
            new ListShopItemsService(fakeDatabase);

    @Test
    void shouldListHeaderAndItem() {
        new FakeDatabaseInitializer(fakeDatabase).initialize();
        ListShopItemsResponse response = service.execute(mockRequest);
        assertEquals(10, response.getShopItems().size());
    }

}
