package core.services.validators.universal.user_input;

import core.responses.CoreError;

import java.util.Optional;

public class InputStringValidator {

    //TODO was there a way to combine strings with params during runtime?
    private static final String ERROR = "Error: ";
    private static final String ERROR_MISSING = " is required.";
    private static final String ERROR_NOT_NUMBER = " should be a number.";
    private static final String ERROR_NEGATIVE = " should not be negative.";
    private static final String ERROR_ZERO_OR_LESS = " should be greater than zero.";
    private static final String ERROR_DECIMAL = " should not be decimal.";

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE = "[0-9]+(.[0-9]+)?";
    private static final String REGEX_GREATER_ZERO = "0*[1-9][0-9]*(.[0-9]+)?";
    private static final String REGEX_NOT_DECIMAL = "-?[0-9]+";

    public Optional<CoreError> validateIsPresent(InputStringValidatorRecord record) {
        return (record.value() == null || record.value().isBlank())
                ? Optional.of(new CoreError(record.field(), ERROR + record.valueName() + ERROR_MISSING))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNumber(InputStringValidatorRecord record) {
        return (exists(record.value()) &&
                !record.value().matches(REGEX_NUMBER))
                ? Optional.of(new CoreError(record.field(), ERROR + record.valueName() + ERROR_NOT_NUMBER))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotNegative(InputStringValidatorRecord record) {
        return (exists(record.value()) &&
                !record.value().matches(REGEX_NOT_NEGATIVE))
                ? Optional.of(new CoreError(record.field(), ERROR + record.valueName() + ERROR_NEGATIVE))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsGreaterThanZero(InputStringValidatorRecord record) {
        return (exists(record.value()) &&
                !record.value().matches(REGEX_GREATER_ZERO))
                ? Optional.of(new CoreError(record.field(), ERROR + record.valueName() + ERROR_ZERO_OR_LESS))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotDecimal(InputStringValidatorRecord record) {
        return (exists(record.value()) &&
                !record.value().matches(REGEX_NOT_DECIMAL))
                ? Optional.of(new CoreError(record.field(), ERROR + record.valueName() + ERROR_DECIMAL))
                : Optional.empty();
    }

    private boolean exists(String value) {
        return value != null && !value.isBlank();
    }

}
