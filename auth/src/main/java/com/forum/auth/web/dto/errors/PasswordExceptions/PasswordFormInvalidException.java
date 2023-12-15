package com.forum.auth.web.dto.errors.PasswordExceptions;

import com.forum.auth.web.dto.errors.DTOException;

public class PasswordFormInvalidException extends DTOException {

    public PasswordFormInvalidException(String message) {
        super(message, "password_invalid");
    }
}
