package core.services.actions.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.CoreError;
import core.responses.manager.ChangeItemDataResponse;
import core.services.validators.manager.ChangeItemDataValidator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ChangeItemDataServiceTest {

    private final Database mockDatabase = mock(Database.class);
    private final ChangeItemDataValidator mockValidator = mock(ChangeItemDataValidator.class);
    private final ChangeItemDataRequest mockRequest = mock(ChangeItemDataRequest.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);

    private final ChangeItemDataService service = new ChangeItemDataService(mockDatabase, mockValidator);

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        ChangeItemDataResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        ChangeItemDataResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("name");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changeName(1L, "name");
    }

    @Test
    void shouldNotUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase, times(0)).changeName(1L, "");
    }

    @Test
    void shouldUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changePrice(1L, new BigDecimal("10.10"));
    }

    @Test
    void shouldNotUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn(" ");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase, times(0)).changePrice(anyLong(), any(BigDecimal.class));
    }

    @Test
    void shouldUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changeAvailableQuantity(1L, 10);
    }

    @Test
    void shouldNotUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn(null);
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase, times(0)).changeAvailableQuantity(anyLong(), anyInt());
    }

    @Test
    void shouldRoundPriceUp() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10776");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changePrice(1L, new BigDecimal("10.11"));
    }

    @Test
    void shouldRoundPriceDown() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.092234");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changePrice(1L, new BigDecimal("10.09"));

    }

}
