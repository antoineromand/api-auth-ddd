package com.dxs.auth.infrastructure.exception;

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