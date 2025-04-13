package com.dxs.auth.web.controller;

import com.dxs.auth.application.GetInfoUserService;
import com.dxs.auth.domain.User;
import com.dxs.auth.web.dto.user.UserInfo;
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
    public UserInfo getInfo() {
        User user = getInfoUserService.getInfo(UUID.randomUUID());
        return new UserInfo(user.getId(), user.getEmail(), user.getRole(), user.isActive(), user.getCreated_at());
    }

}
