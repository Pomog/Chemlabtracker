package core.services.validators.actions.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.domain.item.Item;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.services.validators.universal.user_input.NumberValidator;
import core.services.validators.universal.user_input.PresenceValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddItemToShopValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final PresenceValidator mockPresenceValidator = mock(PresenceValidator.class);
    private final NumberValidator mockNumberValidator = mock(NumberValidator.class);
    private final AddItemToShopRequest mockRequest = mock(AddItemToShopRequest.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final Item mockItem = mock(Item.class);
    private final CoreError mockCoreError = mock(CoreError.class);

    private final AddItemToShopValidator validator =
            new AddItemToShopValidator(mockDatabase, mockPresenceValidator, mockNumberValidator);

    @Test
    void shouldValidateNamePresence() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validateStringIsPresent("name", "name", "Item name");
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
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validateStringIsPresent("100.10", "price", "Price");
    }

    @Test
    void shouldValidatePriceIsNumber() {
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockNumberValidator).validateIsNumber("100.10", "price", "Price");
    }

    @Test
    void shouldValidatePriceIsNotNegative() {
        when(mockRequest.getPrice()).thenReturn("100.10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockNumberValidator).validateIsNotNegative("100.10", "price", "Price");
    }

    @Test
    void shouldValidateQuantityPresence() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validateStringIsPresent("10", "quantity", "Quantity");
    }

    @Test
    void shouldValidateQuantityIsNumber() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockNumberValidator).validateIsNumber("10", "quantity", "Quantity");
    }

    @Test
    void shouldValidateQuantityNotNegative() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockNumberValidator).validateIsNotNegative("10", "quantity", "Quantity");
    }

    @Test
    void shouldValidateQuantityNotDecimal() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        verify(mockNumberValidator).validateIsNotDecimal("10", "quantity", "Quantity");
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockRequest.getItemName()).thenReturn("");
        when(mockRequest.getPrice()).thenReturn("-100.10");
        when(mockRequest.getAvailableQuantity()).thenReturn("10.10");
        when(mockPresenceValidator.validateStringIsPresent("", "name", "Item name")).thenReturn(Optional.of(mockCoreError));
        when(mockNumberValidator.validateIsNotNegative("-100.10", "price", "Price")).thenReturn(Optional.of(mockCoreError));
        when(mockNumberValidator.validateIsNotDecimal("10.10", "quantity", "Quantity")).thenReturn(Optional.of(mockCoreError));
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
        when(mockPresenceValidator.validateStringIsPresent(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(mockNumberValidator.validateIsNumber(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(mockNumberValidator.validateIsNotNegative(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(mockNumberValidator.validateIsNotDecimal(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("name")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
