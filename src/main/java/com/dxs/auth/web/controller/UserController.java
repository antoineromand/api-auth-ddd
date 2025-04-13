package com.dxs.auth.web.controller;

import com.dxs.auth.application.GetInfoUserService;
import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.domain.User;
import com.dxs.auth.web.dto.user.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("private/api/v1/auth/user")
public class UserController {

    private final GetInfoUserService getInfoUserService;

    public UserController(GetInfoUserService getInfoUserService) {
        this.getInfoUserService = getInfoUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfo> getInfo(Authentication authentication) {
        try {
            String userIdString = (String) authentication.getPrincipal();
            UUID userId = UUID.fromString(userIdString);

            User user = getInfoUserService.getInfo(userId);
            UserInfo info = new UserInfo(user.getId(), user.getEmail(), user.getRole(), user.isActive(), user.getCreated_at());

            return ResponseEntity.ok(info);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (AuthenticationFailedException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
