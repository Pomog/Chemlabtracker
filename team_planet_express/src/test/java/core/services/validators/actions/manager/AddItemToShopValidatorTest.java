package core.services.validators.actions.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.domain.item.Item;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.services.validators.shared.PresenceValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddItemToShopValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final PresenceValidator mockPresenceValidator = mock(PresenceValidator.class);
    private final AddItemToShopRequest mockRequest = mock(AddItemToShopRequest.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final Item mockItem = mock(Item.class);

    private final AddItemToShopValidator validator = new AddItemToShopValidator(mockDatabase, mockPresenceValidator);

    @Test
    void shouldValidateNamePresence() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validate("name", "name", "Item name");
    }

    @Test
    void shouldReturnErrorForExistingName() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.of(mockItem));
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("exists"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldValidatePricePresence() {
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validate("100.10", "price", "Price");
    }

    @Test
    void shouldReturnErrorForNonNumberPrice() {
        when(mockRequest.getPrice()).thenReturn("price");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("number"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNegativePrice() {
        when(mockRequest.getPrice()).thenReturn("-100.10");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("negative"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldValidateQuantityPresence() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validate("10", "quantity", "Quantity");
    }

    @Test
    void shouldReturnErrorForNonNumberQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn("quantity");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("number"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNegativeQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn("-10");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("negative"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForDecimalQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10.10");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("decimal"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockRequest.getItemName()).thenReturn("");
        when(mockRequest.getPrice()).thenReturn("-100.10");
        when(mockRequest.getAvailableQuantity()).thenReturn("10.10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnNoErrorsForZeroPrice() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockRequest.getPrice()).thenReturn("0.00");
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }


    @Test
    void shouldReturnNoErrorsForZeroQuantity() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockRequest.getAvailableQuantity()).thenReturn("0");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
