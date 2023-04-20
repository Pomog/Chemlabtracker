package shop.core.services.validators.actions.shared;

import shop.core.database.Database;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class SignInValidator {

    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String VALUE_NAME_LOGIN = "Login name";
    private static final String VALUE_NAME_PASSWORD = "Password";
    private static final String ERROR_LOGIN_NOT_EXISTS = "Error: User with this login does not exist.";
    private static final String ERROR_PASSWORD_INCORRECT = "Error: Password is incorrect.";

    @DIDependency
    private Database database;
    @DIDependency
    private CurrentUserIdValidator userIdValidator;
    @DIDependency
    private InputStringValidator inputStringValidator;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;

    public List<CoreError> validate(SignInRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        if (errors.isEmpty()) {
            validatePasswordMatches(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
        validateLoginNameExists(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        InputStringValidatorRecord record = new InputStringValidatorRecord(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD);
        inputStringValidator.validateIsPresent(record).ifPresent(errors::add);
    }

    private Optional<CoreError> validatePasswordMatches(SignInRequest request) {
        return (!request.getPassword().equals(
                databaseAccessValidator.getUserByLoginName(request.getLoginName()).getPassword()))
                ? Optional.of(new CoreError(FIELD_PASSWORD, ERROR_PASSWORD_INCORRECT))
                : Optional.empty();
    }

    private Optional<CoreError> validateLoginNameExists(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                database.accessUserDatabase().findByLoginName(loginName).isEmpty())
                ? Optional.of(new CoreError(FIELD_LOGIN_NAME, ERROR_LOGIN_NOT_EXISTS))
                : Optional.empty();
    }

}
