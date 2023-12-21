package com.forum.auth.web.dto.exception.PasswordExceptions;

import com.forum.auth.web.dto.exception.DTOException;

public class PasswordFormInvalidException extends DTOException {

    private String message;

    public PasswordFormInvalidException(String message) {
        super(message, "password_invalid");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
