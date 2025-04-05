package com.dxs.auth.web.controller;

import com.dxs.auth.application.LoginUserService;
import com.dxs.auth.application.RegisterUserService;
import com.dxs.auth.application.VerifyTokenService;
import com.dxs.auth.core.usecase.VerifyTokenUseCase;
import com.dxs.auth.domain.User;
import com.dxs.auth.web.dto.LoginInputDTO;
import com.dxs.auth.web.dto.register.RegisterInputDTO;
import com.dxs.auth.web.dto.register.RegisterResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/auth")
public class AuthController {
    private final RegisterUserService registerUserService;
    private final LoginUserService loginUserService;
    private final VerifyTokenService verifyTokenService;

    public AuthController(RegisterUserService registerUserService, LoginUserService loginUserService, VerifyTokenService verifyTokenService) {
        this.registerUserService = registerUserService;
        this.loginUserService = loginUserService;
        this.verifyTokenService = verifyTokenService;
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
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verify-token")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        Cookie[] cookies = Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]);

        Optional<String> jwtCookie = Arrays.stream(cookies)
                .filter(c -> "dxs-cookie-token".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst();

        if (jwtCookie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean isValid = verifyTokenService.verifyToken(jwtCookie.get());

        return isValid
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
