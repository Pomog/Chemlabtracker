package com.fightclub.core.service;

import com.fightclub.core.domain.error.EmailValidationException;
import com.fightclub.core.domain.error.NameValidationException;

public class ValidationService {

    void validateEmail(String email) {

        if (email == null) {
            throw new EmailValidationException("Email is Null");
        }
        if (!email.contains("@")) {
            throw new EmailValidationException("Email doesn't Contains @");
        }
        if (email.length() > 50) {
            throw new EmailValidationException("Email is too Long");
        }
        if (email.length() < 9) {
            throw new EmailValidationException("Email is too Small");
        }
    }

    void validateName(String name) {

        if (name == null) {
            throw new NameValidationException("Name is Null");
        }
        if (name.length() > 20) {
            throw new NameValidationException("Name is too Long");
        }
        if (name.length() < 2) {
            throw new NameValidationException("Name is too Small");
        }
    }

}
