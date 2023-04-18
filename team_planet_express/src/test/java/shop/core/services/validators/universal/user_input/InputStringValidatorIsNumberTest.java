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
class InputStringValidatorIsNumberTest {

    @Mock private InputStringValidatorData mockRecord;

    @InjectMocks private InputStringValidator validator;

    @Test
    void shouldReturnErrorForLetters() {
        when(mockRecord.getValue()).thenReturn("abc");
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNonNumberValue() {
        when(mockRecord.getValue()).thenReturn("#&fml");
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBorkedNumber() {
        when(mockRecord.getValue()).thenReturn("0-23.0040");
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForCharacterMess() {
        when(mockRecord.getValue()).thenReturn("132#re01-dd");
        when(mockRecord.getField()).thenReturn("field");
        when(mockRecord.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        when(mockRecord.getValue()).thenReturn("10");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveNumberWithLeadingZeros() {
        when(mockRecord.getValue()).thenReturn("0010");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeNumber() {
        when(mockRecord.getValue()).thenReturn("-10");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeNumberWithLeadingZeros() {
        when(mockRecord.getValue()).thenReturn("-00010");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForZero() {
        when(mockRecord.getValue()).thenReturn("0");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForMultipleZeros() {
        when(mockRecord.getValue()).thenReturn("0000");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumber() {
        when(mockRecord.getValue()).thenReturn("10.21");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumberWithLeadingZeros() {
        when(mockRecord.getValue()).thenReturn("010.21");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeDecimalNumber() {
        when(mockRecord.getValue()).thenReturn("-10.21");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeDecimalNumberWithLeadingZeros() {
        when(mockRecord.getValue()).thenReturn("-010.21");
        Optional<CoreError> error = validator.validateIsNumber(mockRecord);
        assertTrue(error.isEmpty());
    }

    private void assertCorrectErrorIsPresent(Optional<CoreError> error) {
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("number"));
    }

}
