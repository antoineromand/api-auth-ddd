package com.dxs.auth.web.dto.register;

import com.dxs.auth.core.response.RegisterInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterInputDTO implements RegisterInput {
    private String email;
    private String password;

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
