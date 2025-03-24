package com.dxs.auth.domain.usecase;

import com.dxs.auth.domain.user.UserCredentialsEntity;

public interface LoginUseCase {
    UserCredentialsEntity login(String email, String password);
}
