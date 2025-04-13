package com.dxs.auth.web.controller;

import com.dxs.auth.application.VerifyTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController()
@RequestMapping("private/api/v1/auth")
public class TokenController {
    private final VerifyTokenService verifyTokenService;

    public TokenController(VerifyTokenService verifyTokenService) {
        this.verifyTokenService = verifyTokenService;
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
