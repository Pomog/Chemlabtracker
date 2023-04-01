package core.services.validators.universal.user_input;

import core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputStringValidatorIsPresentTest {

    private final InputStringValidatorRecord mockRecord = mock(InputStringValidatorRecord.class);

    private final InputStringValidator validator = new InputStringValidator();

    @BeforeEach
    void setupSharedMockBehaviour() {
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
    }

    @Test
    void shouldReturnErrorForNullValue() {
        when(mockRecord.value()).thenReturn(null);
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBlankValue() {
        when(mockRecord.value()).thenReturn("");
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForEmptyValue() {
        when(mockRecord.value()).thenReturn(" ");
        Optional<CoreError> error = validator.validateIsPresent(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForValidValue() {
        when(mockRecord.value()).thenReturn("field");
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
