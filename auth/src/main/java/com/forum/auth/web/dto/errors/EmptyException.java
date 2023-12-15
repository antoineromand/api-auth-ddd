package com.forum.auth.web.dto.errors;

public class EmptyException extends DTOException {
    public EmptyException(String message) {
        super(message, "empty_value");
    }
}
