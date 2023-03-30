package core.services.validators.universal.user_input;

import core.responses.CoreError;

import java.util.Optional;

public class PresenceValidator {

    //TODO was there a way to combine strings with params during runtime?
    private static final String ERROR = "Error: ";
    private static final String ERROR_MISSING = " is required.";

    public Optional<CoreError> validateStringIsPresent(String value, String field, String valueName) {
        return (value == null || value.isBlank())
                ? Optional.of(new CoreError(field, ERROR + valueName + ERROR_MISSING))
                : Optional.empty();
    }

}
