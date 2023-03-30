package core.services.validators.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;
import core.support.MutableLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignInValidator {

    private static final String FIELD_USER_ID = "user_id";
    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String ERROR_USER_ID_MISSING = "Error: User id is required.";
    private static final String ERROR_LOGIN_MISSING = "Error: Login name is required.";
    private static final String ERROR_LOGIN_NOT_EXISTS = "Error: User with this login does not exist.";
    private static final String ERROR_PASSWORD_MISSING = "Error: Password is required.";
    private static final String ERROR_PASSWORD_INCORRECT = "Error: Password is incorrect.";

    private final Database database;
    private List<CoreError> errors;

    public SignInValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(SignInRequest request) {
        errors = new ArrayList<>();
        validateUserId(request.getUserId()).ifPresent(errors::add);
        validateLoginName(request.getLoginName());
        validatePassword(request.getPassword());
        if (errors.isEmpty()) {
            validatePasswordMatches(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateUserId(MutableLong userId) {
        return userId == null
                ? Optional.of(new CoreError(FIELD_USER_ID, ERROR_USER_ID_MISSING))
                : Optional.empty();
    }

    private void validateLoginName(String loginName) {
        validateIsPresent(loginName, FIELD_LOGIN_NAME, ERROR_LOGIN_MISSING).ifPresent(errors::add);
        validateLoginNameExists(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password) {
        validateIsPresent(password, FIELD_PASSWORD, ERROR_PASSWORD_MISSING).ifPresent(errors::add);
    }

    private Optional<CoreError> validatePasswordMatches(SignInRequest request) {
        return (!request.getPassword().equals(
                getUserByLoginName(request.getLoginName()).getPassword()))
                ? Optional.of(new CoreError(FIELD_PASSWORD, ERROR_PASSWORD_INCORRECT))
                : Optional.empty();
    }

    private Optional<CoreError> validateIsPresent(String value, String field, String errorMessage) {
        return (value == null || value.isBlank())
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
    }

    private Optional<CoreError> validateLoginNameExists(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                database.accessUserDatabase().findByLogin(loginName).isEmpty())
                ? Optional.of(new CoreError(FIELD_LOGIN_NAME, ERROR_LOGIN_NOT_EXISTS))
                : Optional.empty();
    }

    //TODO yeet, duplicate
    private User getUserByLoginName(String login) {
        return database.accessUserDatabase().findByLogin(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
