package com.forum.auth.web.controller;

import com.forum.auth.web.dto.UserCredentialsDTO;
import com.forum.auth.web.dto.errors.BlankException;
import com.forum.auth.web.dto.errors.EmailInvalidException;
import com.forum.auth.web.dto.errors.EmptyException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordFormInvalidException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordLengthException;
import com.forum.auth.web.dto.validators.EmailValidator;
import com.forum.auth.web.dto.validators.PasswordValidator;
import com.forum.auth.domain.usecase.RegisterUseCase;
import com.forum.auth.web.response.RegisterResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/public/auth")
public class PublicAuthController {
    private final RegisterUseCase registerUseCase;

    public PublicAuthController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody UserCredentialsDTO credentials) {
            try {
                EmailValidator.validate(credentials.getEmail());
                PasswordValidator.validate(credentials.getPassword());
                this.registerUseCase.register(credentials.getEmail(), credentials.getPassword());
            } catch (EmailInvalidException | PasswordFormInvalidException | PasswordLengthException | BlankException |
                     EmptyException e) {
                return ResponseEntity.ok().body(new RegisterResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
            }

        return ResponseEntity.ok().body(new RegisterResponseDTO("User registered successfully", HttpStatus.OK.value()));
    }

    @PostMapping("/login")
    public void login(@RequestBody String body) {
        System.out.println("body: " + body);
    }

}
