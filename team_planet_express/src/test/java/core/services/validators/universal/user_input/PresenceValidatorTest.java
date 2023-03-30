package core.services.validators.universal.user_input;

import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PresenceValidatorTest {

    private final PresenceValidator validator = new PresenceValidator();

    @Test
    void shouldReturnErrorForNullValue() {
        Optional<CoreError> error = validator.validateStringIsPresent(null, "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("required"));
    }

    @Test
    void shouldReturnErrorForBlankValue() {
        Optional<CoreError> error = validator.validateStringIsPresent("", "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("required"));
    }

    @Test
    void shouldReturnErrorForEmptyValue() {
        Optional<CoreError> error = validator.validateStringIsPresent(" ", "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("required"));
    }

    @Test
    void shouldReturnNoErrorForValidValue() {
        Optional<CoreError> error = validator.validateStringIsPresent("field", "field", "Field");
        assertTrue(error.isEmpty());
    }

}
