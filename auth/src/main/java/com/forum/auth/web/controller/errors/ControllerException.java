package com.forum.auth.web.controller.errors;

public class ControllerException extends RuntimeException {

    private String errorCode;
    private Integer statusCode;
    public ControllerException(String message, String errorCode, Integer statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

}
