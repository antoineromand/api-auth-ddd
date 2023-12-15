package com.forum.auth.web.controller;

import com.forum.auth.application.errors.IncorrectPasswordException;
import com.forum.auth.application.errors.UserNotFoundException;
import com.forum.auth.domain.usecase.LoginUseCase;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.infrastructure.config.JwtUtil;
import com.forum.auth.web.dto.UserCredentialsDTO;
import com.forum.auth.web.dto.errors.BlankException;
import com.forum.auth.web.dto.errors.EmailInvalidException;
import com.forum.auth.web.dto.errors.EmptyException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordFormInvalidException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordLengthException;
import com.forum.auth.web.dto.validators.EmailValidator;
import com.forum.auth.web.dto.validators.PasswordValidator;
import com.forum.auth.web.response.AuthResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("api/public/auth/login")
public class PublicAuthLoginController {
    private final LoginUseCase loginUseCase;

    private final JwtUtil jwtUtil;

    @Autowired
    public PublicAuthLoginController(LoginUseCase loginUseCase, JwtUtil jwtUtil) {
        this.loginUseCase = loginUseCase;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserCredentialsDTO credentials, HttpServletResponse response) {
        try {
            EmailValidator.validate(credentials.getEmail());
            PasswordValidator.validate(credentials.getPassword());
            UserCredentialsEntity user = this.loginUseCase.login(credentials.getEmail(), credentials.getPassword());
            ResponseCookie cookie = this.jwtUtil.generateToken(user);
            response.addHeader("Set-Cookie", cookie.toString());
            return ResponseEntity.ok().body(new AuthResponseDTO("User login successfully", HttpStatus.OK.value()));
        } catch (EmailInvalidException | PasswordFormInvalidException | PasswordLengthException | BlankException |
                 EmptyException | UserNotFoundException | IncorrectPasswordException e) {
            return ResponseEntity.ok().body(new AuthResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
