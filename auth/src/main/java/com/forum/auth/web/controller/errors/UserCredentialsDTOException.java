package com.forum.auth.web.controller.errors;

import org.springframework.http.HttpStatus;

public class UserCredentialsDTOException extends ControllerException {

    public UserCredentialsDTOException(String message) {
        super(message, "user_credentials_dto_exception", HttpStatus.BAD_REQUEST.value());
    }
}
