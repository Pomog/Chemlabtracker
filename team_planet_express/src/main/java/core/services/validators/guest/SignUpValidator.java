package core.services.validators.guest;

import core.database.Database;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignUpValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String ERROR_NAME_MISSING = "Error: Name is required.";
    private static final String ERROR_LOGIN_MISSING = "Error: Login name is required.";
    private static final String ERROR_LOGIN_EXISTS = "Error: User with this login name already exists.";
    private static final String ERROR_PASSWORD_MISSING = "Error: Password is required.";
    private static final String ERROR_PASSWORD_SHORT = "Error: Password must be at least 3 characters long.";

    private final Database database;
    private List<CoreError> errors;

    public SignUpValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(SignUpRequest request) {
        errors = new ArrayList<>();
        validateName(request.getName());
        validateLoginName(request.getLoginName());
        validatePassword(request.getPassword());
        return errors;
    }

    private void validateName(String name) {
        validateIsPresent(name, FIELD_NAME, ERROR_NAME_MISSING).ifPresent(errors::add);
    }

    private void validateLoginName(String loginName) {
        validateIsPresent(loginName, FIELD_LOGIN_NAME, ERROR_LOGIN_MISSING).ifPresent(errors::add);
        validateLoginNameDoesNotAlreadyExist(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password) {
        validateIsPresent(password, FIELD_PASSWORD, ERROR_PASSWORD_MISSING).ifPresent(errors::add);
        validatePasswordLength(password).ifPresent(errors::add);
    }

    private Optional<CoreError> validateIsPresent(String value, String field, String errorMessage) {
        return (value == null || value.isBlank())
                ? Optional.of(new CoreError(field, errorMessage))
                : Optional.empty();
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
