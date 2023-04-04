package core.services.validators.universal.system;

import core.services.exception.ServiceMissingDataException;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MutableLongUserIdValidatorTest {

    @Mock private MutableLong mockUserId;

    @InjectMocks private MutableLongUserIdValidator validator;

    @Test
    void shouldThrowMissingDataException() {
        assertThrows(ServiceMissingDataException.class, () -> validator.validateMutableLongUserIdIsPresent(null));
    }

    @Test
    void shouldReturnTrueIfUserIdIsPresent() {
        assertTrue(validator.validateMutableLongUserIdIsPresent(mockUserId));
    }

}
