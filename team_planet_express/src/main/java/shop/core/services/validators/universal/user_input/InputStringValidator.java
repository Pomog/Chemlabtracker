package shop.core.services.validators.universal.user_input;

import shop.core.responses.CoreError;

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

    public Optional<CoreError> validateIsPresent(InputStringValidatorData record) {
        return (record.getValue() == null || record.getValue().isBlank())
                ? Optional.of(new CoreError(record.getField(), ERROR + record.getValueName() + ERROR_MISSING))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNumber(InputStringValidatorData record) {
        return (exists(record.getValue()) &&
                !record.getValue().matches(REGEX_NUMBER))
                ? Optional.of(new CoreError(record.getField(), ERROR + record.getValueName() + ERROR_NOT_NUMBER))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotNegative(InputStringValidatorData record) {
        return (exists(record.getValue()) &&
                !record.getValue().matches(REGEX_NOT_NEGATIVE))
                ? Optional.of(new CoreError(record.getField(), ERROR + record.getValueName() + ERROR_NEGATIVE))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsGreaterThanZero(InputStringValidatorData record) {
        return (exists(record.getValue()) &&
                !record.getValue().matches(REGEX_GREATER_ZERO))
                ? Optional.of(new CoreError(record.getField(), ERROR + record.getValueName() + ERROR_ZERO_OR_LESS))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotDecimal(InputStringValidatorData record) {
        return (exists(record.getValue()) &&
                !record.getValue().matches(REGEX_NOT_DECIMAL))
                ? Optional.of(new CoreError(record.getField(), ERROR + record.getValueName() + ERROR_DECIMAL))
                : Optional.empty();
    }

    private boolean exists(String value) {
        return value != null && !value.isBlank();
    }

}
