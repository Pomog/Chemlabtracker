package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.requests.AddUserRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddUserValidator {
    
    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateSecondName(request).ifPresent(errors::add);
        validateEmail(request).ifPresent(errors::add);
        validatePhoneNumber(request).ifPresent(errors::add);
        return errors;
    }
    
    private Optional<CoreError> validateFirstName(AddUserRequest request) {
        return (request.getFirstName() == null || request.getFirstName().isBlank())
                ? Optional.of(new CoreError("firstName", "Must not be empty"))
                : Optional.empty();
    }
    
    private Optional<CoreError> validateSecondName(AddUserRequest request) {
        return (request.getSecondName() == null || request.getSecondName().isBlank())
                ? Optional.of(new CoreError("secondName", "Must not be empty"))
                : Optional.empty();
    }
    
    private Optional<CoreError> validateEmail(AddUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return Optional.of(new CoreError("email", "Must not be empty"));
        } else if (!request.getEmail().contains("@")) {
            return Optional.of(new CoreError("email", "Must be correct e-mail"));
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<CoreError> validatePhoneNumber(AddUserRequest request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
            return Optional.of(new CoreError("phoneNumber", "Must not be empty"));
        } else if (request.getPhoneNumber().length() < 10) {
            return Optional.of(new CoreError("phoneNumber", "The number is too short"));
        } else if (!request.getPhoneNumber().startsWith("+")
                || !isCorrectNumber(request.getPhoneNumber())) {
            return Optional.of(new CoreError("phoneNumber", "Must be correct phone number"));
        } else {
            return Optional.empty();
        }
    }

    private boolean isCorrectNumber(String phoneNumber) {
        char[] array = phoneNumber.toCharArray();
        for (int i = 1; i < array.length; i++) {
            String mustBeDigit = String.valueOf(array[i]);
            if (mustBeDigit.matches("\\D")) {
                return false;
            }
        }
        return true;
    }

}
