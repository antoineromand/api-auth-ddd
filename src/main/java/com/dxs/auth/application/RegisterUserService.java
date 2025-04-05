package com.dxs.auth.application;

import com.dxs.auth.core.response.Response;
import com.dxs.auth.domain.User;
import com.dxs.auth.infrastructure.adapter.AuthCoreAdapter;
import com.dxs.auth.infrastructure.model.UserModel;
import com.dxs.auth.web.dto.register.RegisterInputDTO;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    private final AuthCoreAdapter authCoreAdapter;

    public RegisterUserService(AuthCoreAdapter authCoreAdapter) {
        this.authCoreAdapter = authCoreAdapter;
    }

    public User register(RegisterInputDTO dto) {
        Response<UserModel> response = authCoreAdapter.register(dto);

        if (!response.isSuccess()) {
            throw new RuntimeException(response.getError());
        }

        UserModel userModel = response.getData();
        return toDomain(userModel);
    }

    private User toDomain(UserModel model) {
        return new User(model.getId(), model.getEmail(), model.getPassword(), model.getRole(), model.isActive(), model.getCreated_at());
    }
}

