package core.services.validators.universal.user_input;

import core.responses.CoreError;

import java.util.Optional;

public class NumberValidator {

    private static final String ERROR = "Error: ";
    private static final String ERROR_NOT_NUMBER = " should be a number.";
    private static final String ERROR_NEGATIVE = " should not be negative.";
    private static final String ERROR_ZERO_OR_LESS = " should be greater than zero.";
    private static final String ERROR_DECIMAL = " should not be decimal.";

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE = "[0-9]+(.[0-9]+)?";
    private static final String REGEX_GREATER_ZERO = "(0*)?[1-9][0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_DECIMAL = "[0-9]+";

    public Optional<CoreError> validateIsNumber(String value, String field, String valueName) {
        return (exists(value) &&
                !value.matches(REGEX_NUMBER))
                ? Optional.of(new CoreError(field, ERROR + valueName + ERROR_NOT_NUMBER))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotNegative(String value, String field, String valueName) {
        return (exists(value) &&
                !value.matches(REGEX_NOT_NEGATIVE))
                ? Optional.of(new CoreError(field, ERROR + valueName + ERROR_NEGATIVE))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsGreaterThanZero(String value, String field, String valueName) {
        return (exists(value) &&
                !value.matches(REGEX_GREATER_ZERO))
                ? Optional.of(new CoreError(field, ERROR + valueName + ERROR_ZERO_OR_LESS))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotDecimal(String value, String field, String valueName) {
        return (exists(value) &&
                !value.matches(REGEX_NOT_DECIMAL))
                ? Optional.of(new CoreError(field, ERROR + valueName + ERROR_DECIMAL))
                : Optional.empty();
    }

    private boolean exists(String value) {
        return value != null && !value.isBlank();
    }

}
