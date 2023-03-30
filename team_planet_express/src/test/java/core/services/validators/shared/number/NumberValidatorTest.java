package core.services.validators.shared.number;

import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberValidatorTest {

    private final NumberValidator validator = new NumberValidator();

    @Test
    void shouldReturnErrorForNonNumberValue() {
        Optional<CoreError> error = validator.validateIsNumber("a", "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("number"));
    }

    @Test
    void shouldReturnNoErrorForNumber() {
        Optional<CoreError> error = validator.validateIsNumber("-10.21", "field", "Field");
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNegativeValue() {
        Optional<CoreError> error = validator.validateIsNotNegative("-10", "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("negative"));
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        Optional<CoreError> error = validator.validateIsNotNegative("10.45", "field", "Field");
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForZero() {
        Optional<CoreError> error = validator.validateIsNotNegative("0.00", "field", "Field");
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForDecimalValue() {
        Optional<CoreError> error = validator.validateIsNotDecimal("10.10", "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("decimal"));
    }

    @Test
    void shouldReturnNoErrorForWholeNumber() {
        Optional<CoreError> error = validator.validateIsNotDecimal("10", "field", "Field");
        assertTrue(error.isEmpty());
    }

}
