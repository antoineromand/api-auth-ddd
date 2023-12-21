package com.forum.auth.web.dto.exception;

public class BlankException extends DTOException{
    public BlankException(String message) {
        super(message, "blank_value");
    }
}
