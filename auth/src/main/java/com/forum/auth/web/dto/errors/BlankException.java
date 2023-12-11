package com.forum.auth.web.dto.errors;

public class BlankException extends ExceptionApplication{
    public BlankException(String message) {
        super(message, "blank_value");
    }
}
