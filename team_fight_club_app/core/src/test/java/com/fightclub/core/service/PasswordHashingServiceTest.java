package com.fightclub.core.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordHashingServiceTest {

    private PasswordHashingService victim = new PasswordHashingService();

    @Test
    public void shouldHashPassword() {

        String password = "111";
        String expectedHash = "698d51a19d8a121ce581499d7b701668";
        String hash = victim.hashPassword(password);
        assertEquals(expectedHash, hash);
        hash = victim.hashPassword(password);
        assertEquals(expectedHash, hash);
    }

}