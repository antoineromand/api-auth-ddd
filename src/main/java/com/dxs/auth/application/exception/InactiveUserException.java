package com.dxs.auth.application.exception;

public class InactiveUserException extends RuntimeException {
    private String code;
    public InactiveUserException(String message) {
        super(message);
        this.code = "inactive_user_exception";
    }
}
