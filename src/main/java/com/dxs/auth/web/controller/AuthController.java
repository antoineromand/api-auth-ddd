package com.dxs.auth.web.controller;

import com.dxs.auth.application.LoginUserService;
import com.dxs.auth.application.RegisterUserService;
import com.dxs.auth.domain.User;
import com.dxs.auth.web.dto.auth.register.LoginInputDTO;
import com.dxs.auth.web.dto.auth.register.RegisterInputDTO;
import com.dxs.auth.web.dto.auth.register.RegisterResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("public/api/v1/auth")
public class AuthController {
    private final RegisterUserService registerUserService;
    private final LoginUserService loginUserService;

    public AuthController(RegisterUserService registerUserService, LoginUserService loginUserService) {
        this.registerUserService = registerUserService;
        this.loginUserService = loginUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterInputDTO input) {
        User savedUser = this.registerUserService.register(input);
        RegisterResponseDTO response = new RegisterResponseDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getCreated_at());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInputDTO input, HttpServletResponse response) {
        String jwt = this.loginUserService.login(input);
        Cookie cookie = new Cookie("dxs-cookie-token", jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        //cookie.setSecure(true);
        response.addCookie(cookie);
        return ResponseEntity.ok().body("User logged in successfully");
    }
}
