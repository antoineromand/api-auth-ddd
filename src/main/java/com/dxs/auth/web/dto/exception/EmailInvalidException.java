package com.dxs.auth.web.dto.exception;

public class EmailInvalidException extends DTOException {
    public EmailInvalidException(String message) {
        super(message, "email_invalid");
    }
}
