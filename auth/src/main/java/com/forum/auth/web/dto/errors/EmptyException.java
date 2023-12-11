package com.forum.auth.web.dto.errors;

public class EmptyException extends ExceptionApplication {
    public EmptyException(String message) {
        super(message, "empty_value");
    }
}
