package core.services.validators.universal.system;

import core.services.exception.ServiceMissingDataException;
import core.support.CurrentUserId;

public class MutableLongUserIdValidator {

    public boolean validateMutableLongUserIdIsPresent(CurrentUserId userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
