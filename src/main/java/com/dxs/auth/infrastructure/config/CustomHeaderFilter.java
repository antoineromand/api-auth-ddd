package com.dxs.auth.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class CustomHeaderFilter extends OncePerRequestFilter {

    private String gatewayUri;

    public CustomHeaderFilter(String gatewayUri) {
        this.gatewayUri = gatewayUri;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.startsWith("/public")) {
            filterChain.doFilter(request, response);
            return;
        }
        if(uri.startsWith("/private/api")) {
            String origin = request.getHeader("Origin");
            String userId = request.getHeader("x-user-id");
            String userRole = request.getHeader("x-user-role");

            if (!gatewayUri.equals(origin)
                    || userId == null || userRole == null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Forbidden: Missing or invalid headers");
                return;
            }

            List<SimpleGrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_" + userRole.toUpperCase()));

            Authentication auth =
                    new UsernamePasswordAuthenticationToken(userId, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}