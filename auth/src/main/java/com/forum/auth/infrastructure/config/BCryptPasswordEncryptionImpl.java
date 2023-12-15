package com.forum.auth.infrastructure.config;

import com.forum.auth.domain.service.IPasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncryptionImpl implements IPasswordEncryption {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BCryptPasswordEncryptionImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public Boolean matches(String rawPassword, String encryptedPassword) {
        return this.passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
