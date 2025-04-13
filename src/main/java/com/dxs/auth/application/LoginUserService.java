package com.dxs.auth.application;

import com.dxs.auth.core.response.Response;
import com.dxs.auth.infrastructure.adapter.AuthCoreAdapter;
import com.dxs.auth.web.dto.auth.register.LoginInputDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {
    private final AuthCoreAdapter authCoreAdapter;

    public LoginUserService(AuthCoreAdapter authCoreAdapter) {
        this.authCoreAdapter = authCoreAdapter;
    }

    public String login(LoginInputDTO input) {
        Response<String> response = authCoreAdapter.login(input.getEmail(), input.getPassword());
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getError());
        }
        return response.getData();
    }
}
