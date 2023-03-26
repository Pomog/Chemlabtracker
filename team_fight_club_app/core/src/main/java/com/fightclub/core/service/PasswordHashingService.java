package com.fightclub.core.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashingService {

    public String hashPassword(String password) {

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        messageDigest.update(password.getBytes());

        byte[] bytes = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}

