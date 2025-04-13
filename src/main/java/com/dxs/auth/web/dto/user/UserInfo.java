package com.dxs.auth.web.dto.user;

import com.dxs.auth.core.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserInfo {
    private UUID id;
    private String email;
    private RoleEnum role;
    private boolean active;
    private LocalDateTime created_at;
}
