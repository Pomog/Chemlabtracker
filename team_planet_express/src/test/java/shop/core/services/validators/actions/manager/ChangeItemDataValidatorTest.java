package shop.core.services.validators.actions.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Database;
import shop.core.database.ItemDatabase;
import shop.core.domain.item.Item;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeItemDataValidatorTest {

    @Mock private Database mockDatabase;
    @Mock private InputStringValidator mockInputStringValidator;
    @Mock private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock private ChangeItemDataRequest mockRequest;
    @Mock private ItemDatabase mockItemDatabase;
    @Mock private Item mockItem;
    @Mock private CoreError mockCoreError;

    @InjectMocks private ChangeItemDataValidator validator;

    @Test
    void shouldValidateId() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("1", "id", "Item id");
        verify(mockInputStringValidator).validateIsPresent(record);
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsNotNegative(record);
        verify(mockInputStringValidator).validateIsNotDecimal(record);
    }

    @Test
    void shouldReturnErrorForNonexistentId() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("does not exist"))
                .findFirst();
        assertFalse(error.isEmpty());
        verify(mockItemDatabase).findById(1L);
    }

    @Test
    void shouldValidatePrice() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.5");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("10.5", "price", "Price");
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsNotNegative(record);
    }

    @Test
    void shouldValidateQuantity() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("5");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("5", "quantity", "Quantity");
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsNotNegative(record);
        verify(mockInputStringValidator).validateIsNotDecimal(record);
    }

    @Test
    void shouldReturnErrorForDuplicate() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("name");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        when(mockItemDatabase.getAllItems()).thenReturn(List.of(new Item("name", new BigDecimal("10.10"), 10)));
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("button"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("exists"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockInputStringValidator.validateIsNotNegative(any(InputStringValidatorRecord.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
