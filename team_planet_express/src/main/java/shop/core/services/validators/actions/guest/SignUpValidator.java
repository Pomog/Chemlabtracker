package shop.core.services.validators.actions.guest;

import shop.core.database.Database;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class SignUpValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String VALUE_NAME_NAME = "Name";
    private static final String VALUE_NAME_LOGIN_NAME = "Login name";
    private static final String VALUE_NAME_PASSWORD = "Password";
    private static final String ERROR_LOGIN_EXISTS = "Error: User with this login name already exists.";
    private static final String ERROR_PASSWORD_SHORT = "Error: Password must be at least 3 characters long.";

    @DIDependency
    private Database database;
    @DIDependency
    private CurrentUserIdValidator userIdValidator;
    @DIDependency
    private InputStringValidator inputStringValidator;

    public List<CoreError> validate(SignUpRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        validateName(request.getName(), errors);
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        return errors;
    }

    private void validateName(String name, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(name, FIELD_NAME, VALUE_NAME_NAME);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN_NAME);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        validateLoginNameDoesNotAlreadyExist(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        validatePasswordLength(password).ifPresent(errors::add);
    }

    private Optional<CoreError> validateLoginNameDoesNotAlreadyExist(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                database.accessUserDatabase().findByLoginName(loginName).isPresent())
                ? Optional.of(new CoreError(FIELD_LOGIN_NAME, ERROR_LOGIN_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validatePasswordLength(String password) {
        return (password != null && !password.isBlank() && password.length() < 3)
                ? Optional.of(new CoreError(FIELD_PASSWORD, ERROR_PASSWORD_SHORT))
                : Optional.empty();
    }

}
