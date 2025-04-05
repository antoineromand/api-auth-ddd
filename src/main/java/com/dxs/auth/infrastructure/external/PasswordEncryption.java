package com.dxs.auth.infrastructure.external;

import com.dxs.auth.core.external.IPasswordEncrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption implements IPasswordEncrypt {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String encrypt(String password) {
        return this.encoder.encode(password);
    }

    public boolean match(String raw, String encoded) {
        return this.encoder.matches(raw, encoded);
    }
}
