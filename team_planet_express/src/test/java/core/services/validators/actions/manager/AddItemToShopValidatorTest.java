package core.services.validators.actions.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.domain.item.Item;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddItemToShopValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final InputStringValidator mockInputStringValidator = mock(InputStringValidator.class);
    private final AddItemToShopRequest mockRequest = mock(AddItemToShopRequest.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final Item mockItem = mock(Item.class);
    private final CoreError mockCoreError = mock(CoreError.class);

    private final AddItemToShopValidator validator =
            new AddItemToShopValidator(mockDatabase, mockInputStringValidator);

    @Test
    void shouldValidateNameIsPresent() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("name", "name", "Item name");
        verify(mockInputStringValidator).validateIsPresent(record);
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
    void shouldValidatePrice() {
        when(mockRequest.getPrice()).thenReturn("100.10");
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("100.10", "price", "Price");
        verify(mockInputStringValidator).validateIsPresent(record);
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsNotNegative(record);
    }

    @Test
    void shouldValidateQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("10", "quantity", "Quantity");
        verify(mockInputStringValidator).validateIsPresent(record);
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsNotNegative(record);
        verify(mockInputStringValidator).validateIsNotDecimal(record);
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorRecord.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    //TODO integration test ?
    @Test
    void shouldReturnNoErrorsForValidInput() {
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
