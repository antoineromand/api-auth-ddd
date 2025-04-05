package com.dxs.auth.infrastructure.adapter;

import com.dxs.auth.core.usecase.RegisterUseCase;
import com.dxs.auth.infrastructure.model.UserModel;
import com.dxs.auth.web.dto.RegisterDTO;

public class AuthCoreAdapter {
    private final RegisterUseCase<UserModel, RegisterDTO> registerUseCase;

    public AuthCoreAdapter() {}
}
