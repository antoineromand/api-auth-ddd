package com.dxs.auth.web.dto.validators;

import com.dxs.auth.web.dto.exception.BlankException;
import com.dxs.auth.web.dto.exception.EmptyException;
import com.dxs.auth.web.dto.exception.PasswordExceptions.PasswordFormInvalidException;
import com.dxs.auth.web.dto.exception.PasswordExceptions.PasswordLengthException;

public class PasswordValidator {

    private static boolean isLengthValid(String password) {
        return password.length() >= 8;
    }

    private static boolean containsAtLeastOneDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private static boolean containsAtLeastOneUpperCaseLetter(String password) {
        return password.matches(".*[A-Z].*");
    }

    private static boolean containsAtLeastOneSpecialCharacter(String password) {
        return password.matches(".*[!@#$%^&*()-+].*");
    }

    public static void validate(String password) {
        if (password == null) {
            throw new NullPointerException("Password must not be null");
        }
        if (password.isEmpty()) {
            throw new EmptyException("Password must not be empty");
        }
        if (password.isBlank()) {
            throw new BlankException("Password must not be blank");
        }
        if (!isLengthValid(password)) {
            throw new PasswordLengthException("Password must be at least 8 characters long");
        }
        if (!containsAtLeastOneDigit(password)) {
            throw new PasswordFormInvalidException("Password must contain at least one digit");
        }
        if (!containsAtLeastOneUpperCaseLetter(password)) {
            throw new PasswordFormInvalidException("Password must contain at least one upper case letter");
        }
        if (!containsAtLeastOneSpecialCharacter(password)) {
            throw new PasswordFormInvalidException("Password must contain at least one special character");
        }
    }
}
