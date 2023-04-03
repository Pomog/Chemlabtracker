package core.services.validators.universal.system;

import core.services.exception.ServiceMissingDataException;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class MutableLongUserIdValidatorTest {

    private final MutableLong mockUserId = mock(MutableLong.class);

    private final MutableLongUserIdValidator validator = new MutableLongUserIdValidator();

    @Test
    void shouldThrowMissingDataException() {
        assertThrows(ServiceMissingDataException.class, () -> validator.validateMutableLongUserIdIsPresent(null));
    }

    @Test
    void shouldReturnTrueIfUserIdIsPresent() {
        assertTrue(validator.validateMutableLongUserIdIsPresent(mockUserId));
    }

}
