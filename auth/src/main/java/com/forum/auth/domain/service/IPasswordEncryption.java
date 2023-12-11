package com.forum.auth.domain.service;

public interface IPasswordEncryption {
    String encryptPassword(String rawPassword);
}
