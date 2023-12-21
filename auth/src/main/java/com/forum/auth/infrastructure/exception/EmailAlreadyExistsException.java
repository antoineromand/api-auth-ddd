package com.forum.auth.infrastructure.exception;

public class EmailAlreadyExistsException extends ExceptionInfrastructure {

    public EmailAlreadyExistsException(String message) {
        super(message, "email_already_exists");
    }
}
