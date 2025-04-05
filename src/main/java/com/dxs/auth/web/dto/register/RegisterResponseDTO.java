package com.dxs.auth.web.dto.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {
    private UUID id;
    private String email;
    private Date created_at;
}
