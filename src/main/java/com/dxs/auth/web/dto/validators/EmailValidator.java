package com.dxs.auth.web.dto.validators;

import com.dxs.auth.web.dto.exception.EmailInvalidException;

public class EmailValidator {
    public static void validate(String email) {
        if (email == null) {
            throw new NullPointerException("The email is undefined. Please, try again.");
        }
        if (!isValidEmail(email)) {
            throw new EmailInvalidException("The email is invalid. Please, try again.");
        }
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
