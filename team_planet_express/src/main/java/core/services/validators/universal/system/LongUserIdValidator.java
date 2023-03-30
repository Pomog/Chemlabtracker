package core.services.validators.universal.system;

import core.services.exception.ServiceMissingDataException;

public class LongUserIdValidator {

    public boolean validateLongUserIdIsPresent(Long userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
