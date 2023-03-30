package core.services.validators.actions.guest;

import core.database.Database;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.services.validators.universal.system.MutableLongUserIdValidator;
import core.services.validators.universal.user_input.PresenceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignUpValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String VALUE_NAME_NAME = "Name";
    private static final String VALUE_NAME_LOGIN_NAME = "Login name";
    private static final String VALUE_NAME_PASSWORD = "Password";
    private static final String ERROR_LOGIN_EXISTS = "Error: User with this login name already exists.";
    private static final String ERROR_PASSWORD_SHORT = "Error: Password must be at least 3 characters long.";

    private final Database database;
    private final MutableLongUserIdValidator userIdValidator;
    private final PresenceValidator presenceValidator;
    private List<CoreError> errors;

    public SignUpValidator(Database database, MutableLongUserIdValidator userIdValidator, PresenceValidator presenceValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.presenceValidator = presenceValidator;
    }

    public List<CoreError> validate(SignUpRequest request) {
        userIdValidator.validateMutableLongUserIdIsPresent(request.getUserId());
        errors = new ArrayList<>();
        validateName(request.getName());
        validateLoginName(request.getLoginName());
        validatePassword(request.getPassword());
        return errors;
    }

    private void validateName(String name) {
        presenceValidator.validateStringIsPresent(name, FIELD_NAME, VALUE_NAME_NAME).ifPresent(errors::add);
    }

    private void validateLoginName(String loginName) {
        presenceValidator.validateStringIsPresent(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN_NAME).ifPresent(errors::add);
        validateLoginNameDoesNotAlreadyExist(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password) {
        presenceValidator.validateStringIsPresent(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD).ifPresent(errors::add);
        validatePasswordLength(password).ifPresent(errors::add);
    }

    private Optional<CoreError> validateLoginNameDoesNotAlreadyExist(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                database.accessUserDatabase().findByLogin(loginName).isPresent())
                ? Optional.of(new CoreError(FIELD_LOGIN_NAME, ERROR_LOGIN_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validatePasswordLength(String password) {
        return (password != null && !password.isBlank() && password.length() < 3)
                ? Optional.of(new CoreError(FIELD_PASSWORD, ERROR_PASSWORD_SHORT))
                : Optional.empty();
    }

}
