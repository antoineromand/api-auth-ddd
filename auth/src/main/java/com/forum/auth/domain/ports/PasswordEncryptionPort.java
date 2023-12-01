package com.forum.auth.domain.ports;

public interface PasswordEncryptionPort {
    String encryptPassword(String rawPassword);
}
