package com.dxs.auth.web.dto.auth.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponseDTO {
    private UUID id;
    private String email;
    private LocalDateTime created_at;

}
