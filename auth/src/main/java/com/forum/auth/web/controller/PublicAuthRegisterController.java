package com.forum.auth.web.controller;

import com.forum.auth.application.exception.EmailAlreadyExistsException;
import com.forum.auth.web.dto.UserCredentialsDTO;
import com.forum.auth.web.dto.exception.BlankException;
import com.forum.auth.web.dto.exception.EmailInvalidException;
import com.forum.auth.web.dto.exception.EmptyException;
import com.forum.auth.web.dto.exception.PasswordExceptions.PasswordFormInvalidException;
import com.forum.auth.web.dto.exception.PasswordExceptions.PasswordLengthException;
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
        EmailValidator.validate(credentials.getEmail());
        PasswordValidator.validate(credentials.getPassword());
        this.registerUseCase.execute(credentials.getEmail(), credentials.getPassword());
        return ResponseEntity.ok().body(new AuthResponseDTO("User registered successfully", HttpStatus.OK.value()));
    }


}
