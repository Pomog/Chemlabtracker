package core.services.actions.customer;

import core.database.Database;
import core.database.ItemDatabase;
import core.requests.customer.ListShopItemsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListShopItemsServiceTest {

    @Mock private Database mockDatabase;
    @Mock private ItemDatabase mockItemDatabase;
    @Mock private ListShopItemsRequest mockRequest;

    @InjectMocks private ListShopItemsService service;

    @Test
    void shouldGetItemsFromDatabase() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).getAllItems();
    }

}
