package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Database;
import shop.core.database.ItemDatabase;
import shop.core.requests.customer.ListShopItemsRequest;

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
