package core.services.validators.universal.system;

import core.services.exception.ServiceMissingDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongUserIdValidatorTest {

    private final LongUserIdValidator validator = new LongUserIdValidator();

    @Test
    void shouldThrowMissingDataException() {
        assertThrows(ServiceMissingDataException.class, () -> validator.validateLongUserIdIsPresent(null));
    }

    @Test
    void shouldReturnTrueIfUserIdIsPresent() {
        assertTrue(validator.validateLongUserIdIsPresent(1L));
    }

}
