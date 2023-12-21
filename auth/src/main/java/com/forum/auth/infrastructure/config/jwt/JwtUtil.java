package com.forum.auth.infrastructure.config.jwt;
import com.forum.auth.domain.user.UserCredentialsEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    @Value("${JWT_SECRET}")
    private String secret_key;

    @Value("${JWT_EXPIRATION}")
    private Long expiration;

    public ResponseCookie generateToken(UserCredentialsEntity user) {
        String token = createToken(user);
        return generateCookieToken(token);
    }

    public String createToken(UserCredentialsEntity user) {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(expiration));
        return Jwts.builder()
                .claims()
                .subject(user.getId().toString())
                .add("role", user.getRole())
                .expiration(tokenValidity)
                .and().signWith(getSigningKey())
                .compact();
    }

    public ResponseCookie generateCookieToken(String generatedToken) {
        return ResponseCookie.from("token", generatedToken)
                .httpOnly(true)
                .path("/")
                .maxAge(expiration * 60)
                .build();
    }

    public String getTokenFromCookie(Cookie[] cookies) {
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret_key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Map<String, Object> parseTokenToMap(String token) {
        return (Map<String, Object>) Jwts.parser().verifyWith(getSigningKey()).build().parse(token).getBody();
    }

}
