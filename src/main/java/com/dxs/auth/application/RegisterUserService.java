package com.dxs.auth.application;

import com.dxs.auth.core.response.Response;
import com.dxs.auth.domain.User;
import com.dxs.auth.infrastructure.adapter.AuthCoreAdapter;
import com.dxs.auth.infrastructure.model.UserModel;
import com.dxs.auth.web.dto.RegisterDTO;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    private final AuthCoreAdapter authCoreAdapter;

    public RegisterUserService(AuthCoreAdapter authCoreAdapter) {
        this.authCoreAdapter = authCoreAdapter;
    }

    public User register(RegisterDTO dto) {
        Response<UserModel> response = authCoreAdapter.register(dto);

        if (!response.isSuccess()) {
            throw new RuntimeException(response.getError()); // ou une exception métier spécifique
        }

        UserModel userModel = response.getData();
        return toDomain(userModel);
    }

    private User toDomain(UserModel model) {
        return new User(model.getUuid(), model.getEmail());
    }
}

