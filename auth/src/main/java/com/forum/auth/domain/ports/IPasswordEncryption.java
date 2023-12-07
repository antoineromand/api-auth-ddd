package com.forum.auth.domain.ports;

public interface IPasswordEncryption {
    String encryptPassword(String rawPassword);
}
