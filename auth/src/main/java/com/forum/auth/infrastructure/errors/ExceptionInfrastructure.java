package com.forum.auth.infrastructure.errors;

public class ExceptionInfrastructure extends RuntimeException {
    private final String errorCode;

    protected ExceptionInfrastructure(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}