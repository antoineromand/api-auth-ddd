package com.forum.auth.application.errors;

public class BlankException extends ExceptionApplication{
    public BlankException(String message) {
        super(message, "blank_value");
    }
}
