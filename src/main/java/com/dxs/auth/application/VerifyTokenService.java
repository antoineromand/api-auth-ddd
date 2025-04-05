package com.dxs.auth.application;

import com.dxs.auth.infrastructure.adapter.AuthCoreAdapter;
import org.springframework.stereotype.Service;

@Service
public class VerifyTokenService {
    private final AuthCoreAdapter authCoreAdapter;

    public VerifyTokenService(AuthCoreAdapter authCoreAdapter) {
        this.authCoreAdapter = authCoreAdapter;
    }

    public boolean verifyToken(String jwt) {
        return this.authCoreAdapter.verifyToken(jwt);
    }
}
