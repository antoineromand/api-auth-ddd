package com.forum.auth.web.dto.errors.PasswordExceptions;

import com.forum.auth.web.dto.errors.DTOException;

public class PasswordLengthException extends DTOException {
    public PasswordLengthException(String message) {
        super(message, "password_length");
    }
}
