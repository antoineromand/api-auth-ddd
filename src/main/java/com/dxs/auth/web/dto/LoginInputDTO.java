package com.dxs.auth.web.dto;

import com.dxs.auth.core.response.RegisterInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginInputDTO implements RegisterInput {
    private String email;
    private String password;
}