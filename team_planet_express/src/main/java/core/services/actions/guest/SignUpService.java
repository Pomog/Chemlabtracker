package core.services.actions.guest;

import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.responses.guest.SignUpResponse;
import core.services.validators.guest.SignUpValidator;

import java.util.List;

public class SignUpService {

    private final Database database;
    private final SignUpValidator validator;

    public SignUpService(Database database, SignUpValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SignUpResponse execute(SignUpRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignUpResponse(errors);
        }
        String name = request.getName();
        String loginName = request.getLoginName();
        String password = request.getPassword();
        User createdUser = database.accessUserDatabase().save(new User(name, loginName, password, UserRole.CUSTOMER));
        request.getUserId().setValue(createdUser.getId());
        return new SignUpResponse(createdUser);
    }

}
