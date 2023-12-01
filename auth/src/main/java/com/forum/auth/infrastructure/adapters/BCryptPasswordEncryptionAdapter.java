package com.forum.auth.infrastructure.adapters;

import com.forum.auth.domain.ports.PasswordEncryptionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service()
public class BCryptPasswordEncryptionAdapter implements PasswordEncryptionPort {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BCryptPasswordEncryptionAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
