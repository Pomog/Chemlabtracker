package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.user.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.user.SetUserNotActiveResponse;
import lv.javaguru.java2.servify.domain.FieldTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetUserNotActiveService {
    @Autowired private UsersDatabase userDB;

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
