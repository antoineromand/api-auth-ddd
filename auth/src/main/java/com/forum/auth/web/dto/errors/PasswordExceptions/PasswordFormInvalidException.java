package com.forum.auth.web.dto.errors.PasswordExceptions;

import com.forum.auth.web.dto.errors.ExceptionApplication;

public class PasswordFormInvalidException extends ExceptionApplication {

    public PasswordFormInvalidException(String message) {
        super(message, "password_invalid");
    }
}
