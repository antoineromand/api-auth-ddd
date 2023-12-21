package com.forum.auth.infrastructure.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    private RequestMatcher requestMatcher;


    public JwtAuthenticationFilter(JwtUtil jwtUtil, RequestMatcher requestMatcher) {
        this.jwtUtil = jwtUtil;
        this.requestMatcher = requestMatcher;
    }


    private Authentication getAuthentication(String token) {
        Map<String, Object> claims = this.jwtUtil.parseTokenToMap(token);
        String userId = (String) claims.get("sub");
        String userRole = claims.get("role").toString().toUpperCase();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userRole));
        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (this.requestMatcher.matches(request)) {
            String token = this.jwtUtil.getTokenFromCookie(request.getCookies());
            if (token != null) {
                Authentication authentication = this.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
