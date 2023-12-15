package com.forum.auth.application.errors;

public class UserNotFoundException extends RuntimeException {
    private String code;
    public UserNotFoundException(String message) {
        super(message);
        this.code = "user_not_found";
    }
}
