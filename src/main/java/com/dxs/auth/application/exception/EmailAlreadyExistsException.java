package com.dxs.auth.application.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private String code;
    public EmailAlreadyExistsException(String message) {
        super(message);
        this.code = "email_already_exists_exception";
    }
}
