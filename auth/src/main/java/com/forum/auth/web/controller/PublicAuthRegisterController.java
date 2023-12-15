package com.forum.auth.web.controller;

import com.forum.auth.application.errors.EmailAlreadyExistsException;
import com.forum.auth.application.errors.IncorrectPasswordException;
import com.forum.auth.application.errors.UserNotFoundException;
import com.forum.auth.domain.usecase.LoginUseCase;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.web.dto.UserCredentialsDTO;
import com.forum.auth.web.dto.errors.BlankException;
import com.forum.auth.web.dto.errors.EmailInvalidException;
import com.forum.auth.web.dto.errors.EmptyException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordFormInvalidException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordLengthException;
import com.forum.auth.web.dto.validators.EmailValidator;
import com.forum.auth.web.dto.validators.PasswordValidator;
import com.forum.auth.domain.usecase.RegisterUseCase;
import com.forum.auth.web.response.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/public/auth/register")
public class PublicAuthRegisterController {
    private final RegisterUseCase registerUseCase;


    @Autowired
    public PublicAuthRegisterController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserCredentialsDTO credentials) {
            try {
                EmailValidator.validate(credentials.getEmail());
                PasswordValidator.validate(credentials.getPassword());
                this.registerUseCase.register(credentials.getEmail(), credentials.getPassword());
                return ResponseEntity.ok().body(new AuthResponseDTO("User registered successfully", HttpStatus.OK.value()));
            } catch (EmailInvalidException | PasswordFormInvalidException | PasswordLengthException | BlankException |
                     EmptyException | EmailAlreadyExistsException e) {
                return ResponseEntity.ok().body(new AuthResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
            }
    }
}
