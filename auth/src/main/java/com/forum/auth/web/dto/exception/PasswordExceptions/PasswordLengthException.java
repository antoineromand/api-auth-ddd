package com.forum.auth.web.dto.exception.PasswordExceptions;

import com.forum.auth.web.dto.exception.DTOException;

public class PasswordLengthException extends DTOException {
    public PasswordLengthException(String message) {
        super(message, "password_length");
    }
}
