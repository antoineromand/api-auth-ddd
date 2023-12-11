package com.forum.auth.web.dto.errors.PasswordExceptions;

import com.forum.auth.web.dto.errors.ExceptionApplication;

public class PasswordLengthException extends ExceptionApplication {
    public PasswordLengthException(String message) {
        super(message, "password_length");
    }
}
