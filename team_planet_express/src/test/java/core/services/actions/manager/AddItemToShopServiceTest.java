package core.services.actions.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.domain.item.Item;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.responses.manager.AddItemToShopResponse;
import core.services.validators.actions.manager.AddItemToShopValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddItemToShopServiceTest {

    @Mock private Database mockDatabase;
    @Mock private AddItemToShopValidator mockValidator;
    @Mock private AddItemToShopRequest mockRequest;
    @Mock private CoreError mockCoreError;
    @Mock private ItemDatabase mockItemDatabase;

    @InjectMocks private AddItemToShopService service;

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        AddItemToShopResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        AddItemToShopResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldSaveValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase)
                .save(new Item("name", new BigDecimal("100.10"), 10));
    }

    @Test
    void shouldRoundPriceUp() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockRequest.getPrice()).thenReturn("100.10755");
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase)
                .save(new Item("name", new BigDecimal("100.11"), 10));
    }

    @Test
    void shouldRoundPriceDown() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockRequest.getPrice()).thenReturn("99.102234");
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase)
                .save(new Item("name", new BigDecimal("99.10"), 10));
    }

}
