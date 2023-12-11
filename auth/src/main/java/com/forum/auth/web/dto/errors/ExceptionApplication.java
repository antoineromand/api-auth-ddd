package com.forum.auth.web.dto.errors;

public abstract class ExceptionApplication extends RuntimeException {
    private final String errorCode;

    protected ExceptionApplication(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
