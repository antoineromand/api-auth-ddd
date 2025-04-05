package com.dxs.auth.application;

import com.dxs.auth.core.response.Response;
import com.dxs.auth.infrastructure.adapter.AuthCoreAdapter;
import com.dxs.auth.infrastructure.model.UserModel;
import com.dxs.auth.infrastructure.repository.JPAUserModelRepository;
import com.dxs.auth.web.dto.LoginInputDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
