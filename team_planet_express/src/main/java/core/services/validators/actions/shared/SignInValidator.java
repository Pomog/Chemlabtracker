package core.services.validators.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.universal.system.MutableLongUserIdValidator;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignInValidator {

    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String VALUE_NAME_LOGIN = "Login name";
    private static final String VALUE_NAME_PASSWORD = "Password";
    private static final String ERROR_LOGIN_NOT_EXISTS = "Error: User with this login does not exist.";
    private static final String ERROR_PASSWORD_INCORRECT = "Error: Password is incorrect.";

    private final Database database;
    private final MutableLongUserIdValidator userIdValidator;
    private final InputStringValidator inputStringValidator;
    private List<CoreError> errors;

    public SignInValidator(Database database, MutableLongUserIdValidator userIdValidator, InputStringValidator inputStringValidator) {
        this.database = database;
        this.userIdValidator = userIdValidator;
        this.inputStringValidator = inputStringValidator;
    }

    public List<CoreError> validate(SignInRequest request) {
        userIdValidator.validateMutableLongUserIdIsPresent(request.getUserId());
        errors = new ArrayList<>();
        validateLoginName(request.getLoginName());
        validatePassword(request.getPassword());
        if (errors.isEmpty()) {
            validatePasswordMatches(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateLoginName(String loginName) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        validateLoginNameExists(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
    }

    private Optional<CoreError> validatePasswordMatches(SignInRequest request) {
        return (!request.getPassword().equals(
                getUserByLoginName(request.getLoginName()).getPassword()))
                ? Optional.of(new CoreError(FIELD_PASSWORD, ERROR_PASSWORD_INCORRECT))
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
