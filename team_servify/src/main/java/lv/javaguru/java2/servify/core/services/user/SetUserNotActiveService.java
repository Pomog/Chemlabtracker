package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.user.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.user.SetUserNotActiveResponse;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.domain.FieldTitle;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SetUserNotActiveService {
    @DIDependency private UsersDatabase userDB;

    public SetUserNotActiveResponse execute(SetUserNotActiveRequest request) {
        if (request.getUserIdToSetInactive() == null) {
            CoreError error = new CoreError(FieldTitle.ID, "Must not be empty");
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new SetUserNotActiveResponse(errors);
        }
        boolean isUserInactivated = userDB.deactivateUser(request.getUserIdToSetInactive());
        return new SetUserNotActiveResponse(isUserInactivated);
    }
}
