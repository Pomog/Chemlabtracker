package core.services.validators.universal.system;

import core.services.exception.ServiceMissingDataException;
import core.support.MutableLong;

public class MutableLongUserIdValidator {

    public boolean validateMutableLongUserIdIsPresent(MutableLong userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
