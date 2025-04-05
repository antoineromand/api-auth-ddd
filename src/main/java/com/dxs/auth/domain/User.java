package com.dxs.auth.domain;

import com.dxs.auth.core.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    private UUID id;
    private String email;
    private String password;
    private RoleEnum role;
    private boolean active;
    private LocalDateTime created_at;
}
