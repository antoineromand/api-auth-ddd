package com.forum.auth.application.errors.PasswordExceptions;

import com.forum.auth.application.errors.ExceptionApplication;

public class PasswordFormInvalidException extends ExceptionApplication {

    public PasswordFormInvalidException(String message) {
        super(message, "password_invalid");
    }
}
