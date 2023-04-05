package shop.core.services.validators.universal.system;

import shop.core.services.exception.ServiceMissingDataException;
import shop.core.support.CurrentUserId;

public class CurrentUserIdValidator {

    public boolean validateCurrentUserIdIsPresent(CurrentUserId userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
