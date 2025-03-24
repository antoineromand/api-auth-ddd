package com.dxs.auth.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/private/auth")
public class PrivateAuthController {
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getName(Authentication authentication) {
        return authentication.getName();
    }
}
