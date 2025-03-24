package com.dxs.auth.web.controller.exception;

import com.dxs.auth.application.exception.EmailAlreadyExistsException;
import com.dxs.auth.application.exception.InactiveUserException;
import com.dxs.auth.application.exception.IncorrectPasswordException;
import com.dxs.auth.application.exception.UserNotFoundException;
import com.dxs.auth.web.dto.exception.BlankException;
import com.dxs.auth.web.dto.exception.EmailInvalidException;
import com.dxs.auth.web.dto.exception.EmptyException;
import com.dxs.auth.web.dto.exception.PasswordExceptions.PasswordFormInvalidException;
import com.dxs.auth.web.dto.exception.PasswordExceptions.PasswordLengthException;
import com.dxs.auth.web.response.AuthResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class AuthControllerException {
    @ExceptionHandler(EmailInvalidException.class)
    public ResponseEntity<AuthResponseDTO> handleEmailInvalidException(EmailInvalidException e) {
        return new ResponseEntity<>(new AuthResponseDTO("Email is invalid", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordFormInvalidException.class)
    public ResponseEntity<AuthResponseDTO> handlePasswordFormInvalidException(PasswordFormInvalidException e) {
        return new ResponseEntity<>(new AuthResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordLengthException.class)
    public ResponseEntity<AuthResponseDTO> handlePasswordLengthException(PasswordLengthException e) {
        return new ResponseEntity<>(new AuthResponseDTO("Password must be between 8 and 20 characters", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlankException.class)
    public ResponseEntity<AuthResponseDTO> handleBlankException(BlankException e) {
        return new ResponseEntity<>(new AuthResponseDTO("Email and password must not be blank", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<AuthResponseDTO> handleEmptyException(EmptyException e) {
        return new ResponseEntity<>(new AuthResponseDTO("Email and password must not be empty", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AuthResponseDTO> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new AuthResponseDTO("User not found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<AuthResponseDTO> handleIncorrectPasswordException(IncorrectPasswordException e) {
        return new ResponseEntity<>(new AuthResponseDTO("Incorrect password", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InactiveUserException.class)
    public ResponseEntity<AuthResponseDTO> handleInactiveUserException(InactiveUserException e) {
        return new ResponseEntity<>(new AuthResponseDTO("User must activate account first", HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<AuthResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return new ResponseEntity<>(new AuthResponseDTO("Email already exists", HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<AuthResponseDTO> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>(new AuthResponseDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
