package shop.core.services.validators.actions.guest;

import shop.core.database.Database;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

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
    private final CurrentUserIdValidator userIdValidator;
    private final InputStringValidator inputStringValidator;

    public SignUpValidator(Database database, CurrentUserIdValidator userIdValidator, InputStringValidator inputStringValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(SignUpRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        validateName(request.getName(), errors);
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        return errors;
    }

    private void validateName(String name, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(name, FIELD_NAME, VALUE_NAME_NAME);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN_NAME);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateLoginNameDoesNotAlreadyExist(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
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
