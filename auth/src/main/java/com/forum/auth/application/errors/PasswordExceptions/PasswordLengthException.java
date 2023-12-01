package com.forum.auth.application.errors.PasswordExceptions;

import com.forum.auth.application.errors.ExceptionApplication;

public class PasswordLengthException extends ExceptionApplication {
    public PasswordLengthException(String message) {
        super(message, "password_length");
    }
}
