package com.fightclub.core.domain.error;

public class EmailValidationException extends RuntimeException{

    public EmailValidationException(String message) {
        super(message);
    }
}
