package com.fightclub.core.domain.error;

public class NameValidationException extends RuntimeException{

    public NameValidationException(String message) {
        super(message);
    }
}
