package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.responses.CoreError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorIsPresentTest {

    @Mock private InputStringValidatorData mockRecord;

    @InjectMocks private InputStringValidator validator;

    @Test
    void shouldReturnErrorForNullValue() {
        when(mockRecord.getValue()).thenReturn(null);
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBlankValue() {
        when(mockRecord.getValue()).thenReturn("");
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForEmptyValue() {
        when(mockRecord.getValue()).thenReturn(" ");
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForValidValue() {
        when(mockRecord.getValue()).thenReturn("field");
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertTrue(error.isEmpty());
    }

    private void assertCorrectErrorIsPresent(Optional<CoreError> error) {
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("required"));
    }

}
