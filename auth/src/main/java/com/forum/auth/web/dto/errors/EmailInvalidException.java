package com.forum.auth.web.dto.errors;

public class EmailInvalidException extends DTOException {
    public EmailInvalidException(String message) {
        super(message, "email_invalid");
    }
}
