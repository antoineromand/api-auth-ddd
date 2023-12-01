package com.forum.auth.application.errors;

public class EmptyException extends ExceptionApplication {
    public EmptyException(String message) {
        super(message, "empty_value");
    }
}
