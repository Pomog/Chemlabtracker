package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.responses.CoreError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorCompoundTest {

    @Mock private InputStringValidatorData mockInputStringValidatorData;

    @InjectMocks private InputStringValidator validator;

    @Test
    void shouldReturnOneErrorForLettersInIsNumberNotNegative() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberNotNegative(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "number");
    }

    @Test
    void shouldReturnOneErrorForNegativeInIsNumberNotNegative() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberNotNegative(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "negative");
    }

    @Test
    void shouldReturnOneErrorForLettersInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "number");
    }

    @Test
    void shouldReturnOneErrorForNegativeNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-17");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "negative");
    }

    @Test
    void shouldReturnOneErrorForDecimalNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("11.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "decimal");
    }

    @Test
    void shouldReturnTwoErrorsForNegativeDecimalNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(2, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "negative");
        assertCorrectErrorIsPresent(errors.get(1), "decimal");
    }

    @Test
    void shouldReturnOneErrorForLettersInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "number");
    }

    @Test
    void shouldReturnOneErrorForZeroInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "greater");
    }

    @Test
    void shouldReturnOneErrorForNegativeNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-17");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "greater");
    }

    @Test
    void shouldReturnOneErrorForDecimalNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("11.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "decimal");
    }

    @Test
    void shouldReturnTwoErrorsForNegativeDecimalNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(2, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "greater");
        assertCorrectErrorIsPresent(errors.get(1), "decimal");
    }

    private void assertCorrectErrorIsPresent(CoreError error, String errorText) {
        assertEquals("field", error.getField());
        assertTrue(error.getMessage().contains("Field"));
        assertTrue(error.getMessage().toLowerCase().contains(errorText));
    }

}