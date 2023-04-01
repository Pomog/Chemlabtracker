package com.fightclub.in.rest;

import com.fightclub.core.domain.error.EmailValidationException;
import com.fightclub.core.domain.error.NameValidationException;
import com.fightclub.in.rest.api.RegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(EmailValidationException.class)
    public ResponseEntity<RegistrationResponse> handleEmailError(EmailValidationException emailException) {

        RegistrationResponse registrationResponse = new RegistrationResponse(null, emailException.getMessage());
        return ResponseEntity.badRequest().body(registrationResponse);
    }

    @ExceptionHandler(NameValidationException.class)
    public ResponseEntity<RegistrationResponse> handleNameError(NameValidationException exception) {

        RegistrationResponse registrationResponse = new RegistrationResponse(null, exception.getMessage());
        return ResponseEntity.badRequest().body(registrationResponse);
    }


}
