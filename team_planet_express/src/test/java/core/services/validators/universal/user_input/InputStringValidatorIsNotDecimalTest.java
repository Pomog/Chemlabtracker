package core.services.validators.universal.user_input;

import core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputStringValidatorIsNotDecimalTest {

    private final InputStringValidatorRecord mockRecord = mock(InputStringValidatorRecord.class);

    private final InputStringValidator validator = new InputStringValidator();

    @BeforeEach
    void setupSharedMockBehaviour() {
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
    }

    @Test
    void shouldReturnErrorForLetters() {
        when(mockRecord.value()).thenReturn("abc");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNonNumberValue() {
        when(mockRecord.value()).thenReturn("#&fml");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBorkedNumber() {
        when(mockRecord.value()).thenReturn("0-23.0040");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForCharacterMess() {
        when(mockRecord.value()).thenReturn("132#re01-dd");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumber() {
        when(mockRecord.value()).thenReturn("10.21");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("010.21");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForNegativeDecimalNumber() {
        when(mockRecord.value()).thenReturn("-10.21");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForNegativeDecimalNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("-010.21");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        when(mockRecord.value()).thenReturn("10");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("0010");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeNumber() {
        when(mockRecord.value()).thenReturn("-10");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("-00010");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForZero() {
        when(mockRecord.value()).thenReturn("0");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForMultipleZeros() {
        when(mockRecord.value()).thenReturn("0000");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockRecord);
        assertTrue(error.isEmpty());
    }

    private void assertCorrectErrorIsPresent(Optional<CoreError> error) {
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("decimal"));
    }

}
