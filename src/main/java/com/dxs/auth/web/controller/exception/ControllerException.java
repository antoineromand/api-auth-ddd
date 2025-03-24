package com.dxs.auth.web.controller.exception;

public class ControllerException extends RuntimeException {

    private String errorCode;
    private Integer statusCode;
    public ControllerException(String message, String errorCode, Integer statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

}
