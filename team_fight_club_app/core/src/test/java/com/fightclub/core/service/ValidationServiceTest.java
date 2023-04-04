package com.fightclub.core.service;

import com.fightclub.core.domain.error.EmailValidationException;
import com.fightclub.core.domain.error.NameValidationException;
import org.junit.Test;

public class ValidationServiceTest {

    private ValidationService victim = new ValidationService();

    @Test
    public void shouldPassEmailValidation() {

        victim.validateEmail("email@box.com");
    }

    @Test
    public void shouldPassNameValidation() {

        victim.validateName("John Doe");
    }

    @Test(expected = EmailValidationException.class)
    public void emailMustNotBeNull() {

        victim.validateEmail(null);
    }

    @Test(expected = EmailValidationException.class)
    public void emailMustContainEt() {

        victim.validateEmail("No Dog");
    }

    @Test(expected = EmailValidationException.class)
    public void emailShouldNotBeLongThen50() {

        victim.validateEmail("123456789012345678901234567890123456789012345678901234567890@");
    }

    @Test(expected = EmailValidationException.class)
    public void emailShouldNotBeSmallThen9() {

        victim.validateEmail("l@box.lv");
    }


    @Test(expected = NameValidationException.class)
    public void nameShouldNotBeSmallThen2() {

        victim.validateName("i");
    }

    @Test(expected = NameValidationException.class)
    public void nameShouldNotBeNull() {

        victim.validateName(null);
    }

    @Test(expected = NameValidationException.class)
    public void nameShouldNotBeLongThen20() {

        victim.validateName("123456789012345678901234567890");
    }
}
