package com.dxs.auth.domain.usecase;

public interface RegisterUseCase {
    void execute(String email, String password);
}
