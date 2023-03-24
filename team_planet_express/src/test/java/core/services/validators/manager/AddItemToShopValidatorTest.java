package core.services.validators.manager;

import core.database.Database;
import core.database.ItemDatabase;
import core.domain.item.Item;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddItemToShopValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final AddItemToShopRequest mockRequest = mock(AddItemToShopRequest.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final Item mockItem = mock(Item.class);

    private final AddItemToShopValidator validator = new AddItemToShopValidator(mockDatabase);

    @Test
    void shouldReturnErrorForNullName() {
        when(mockRequest.getItemName()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankName() {
        when(mockRequest.getItemName()).thenReturn("");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName("")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyName() {
        when(mockRequest.getItemName()).thenReturn(" ");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findByName(" ")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
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
    void shouldReturnErrorForNullPrice() {
        when(mockRequest.getPrice()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankPrice() {
        when(mockRequest.getPrice()).thenReturn("");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyPrice() {
        when(mockRequest.getPrice()).thenReturn(" ");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("price"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
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
    void shouldReturnErrorForNullQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn("");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn(" ");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("quantity"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
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
