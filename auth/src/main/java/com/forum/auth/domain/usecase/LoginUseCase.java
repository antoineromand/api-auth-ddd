package com.forum.auth.domain.usecase;

import com.forum.auth.domain.user.UserCredentialsEntity;

public interface LoginUseCase {
    UserCredentialsEntity login(String email, String password);
}
