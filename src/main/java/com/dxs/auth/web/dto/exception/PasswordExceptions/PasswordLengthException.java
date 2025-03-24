package com.dxs.auth.web.dto.exception.PasswordExceptions;

import com.dxs.auth.web.dto.exception.DTOException;

public class PasswordLengthException extends DTOException {
    public PasswordLengthException(String message) {
        super(message, "password_length");
    }
}
