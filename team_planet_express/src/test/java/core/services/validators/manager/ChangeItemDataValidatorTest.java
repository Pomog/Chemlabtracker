package core.services.validators.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.domain.item.Item;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ChangeItemDataValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final ChangeItemDataRequest mockRequest = mock(ChangeItemDataRequest.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final Item mockItem = mock(Item.class);

    private final ChangeItemDataValidator validator = new ChangeItemDataValidator(mockDatabase);

    @Test
    void shouldReturnErrorForNullId() {
        when(mockRequest.getItemId()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankId() {
        when(mockRequest.getItemId()).thenReturn("");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyId() {
        when(mockRequest.getItemId()).thenReturn(" ");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNonNumberId() {
        when(mockRequest.getItemId()).thenReturn("id");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("number"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNegativeId() {
        when(mockRequest.getItemId()).thenReturn("-1");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("negative"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForDecimalId() {
        when(mockRequest.getItemId()).thenReturn("1.5");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("id"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("decimal"))
                .findFirst();
        assertFalse(error.isEmpty());
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
    void shouldReturnErrorForNonNumberPrice() {
        when(mockRequest.getNewPrice()).thenReturn("price");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("number"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNegativePrice() {
        when(mockRequest.getNewPrice()).thenReturn("-100.10");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("negative"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNonNumberQuantity() {
        when(mockRequest.getNewAvailableQuantity()).thenReturn("quantity");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("number"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNegativeQuantity() {
        when(mockRequest.getNewAvailableQuantity()).thenReturn("-10");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("negative"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForDecimalQuantity() {
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10.10");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("decimal"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForDuplicate() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("name");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        //TODO this should be in Item.equals() test
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
        when(mockRequest.getItemId()).thenReturn("-1");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("1.5");
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockItemDatabase.getAllItems()).thenReturn(Collections.emptyList());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnNoErrorsForZeroPrice() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("0.00");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockItemDatabase.getAllItems()).thenReturn(Collections.emptyList());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }


    @Test
    void shouldReturnNoErrorsForZeroQuantity() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("0");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockItemDatabase.getAllItems()).thenReturn(Collections.emptyList());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
