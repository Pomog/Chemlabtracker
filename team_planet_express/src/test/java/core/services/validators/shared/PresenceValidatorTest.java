package core.services.validators.shared;

import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PresenceValidatorTest {

    private final PresenceValidator validator = new PresenceValidator();

    @Test
    void shouldReturnErrorForNullValue() {
        Optional<CoreError> error = validator.validate(null, "field", "Field");
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().toLowerCase().contains("required"));
    }

    //TODO test "" "  "
    //TODO test ok

}
