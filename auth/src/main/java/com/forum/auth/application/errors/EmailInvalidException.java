package com.forum.auth.application.errors;

public class EmailInvalidException extends ExceptionApplication {
    public EmailInvalidException(String message) {
        super(message, "email_invalid");
    }
}
