package com.dxs.auth.application.exception;

public class IncorrectPasswordException extends RuntimeException {
    private String code;
    public IncorrectPasswordException(String message) {
        super(message);
        this.code = "incorrect_password";
    }
}
