package com.forum.auth.web.controller;

import com.forum.auth.domain.usecase.LoginUseCase;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.infrastructure.config.jwt.JwtUtil;
import com.forum.auth.web.dto.UserCredentialsDTO;
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
        EmailValidator.validate(credentials.getEmail());
        PasswordValidator.validate(credentials.getPassword());
        UserCredentialsEntity user = this.loginUseCase.login(credentials.getEmail(), credentials.getPassword());
        ResponseCookie cookie = this.jwtUtil.generateToken(user);
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok().body(new AuthResponseDTO("User login successfully", HttpStatus.OK.value()));
    }
}
