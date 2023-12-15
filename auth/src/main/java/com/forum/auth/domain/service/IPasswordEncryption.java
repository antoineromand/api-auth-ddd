package com.forum.auth.domain.service;

import org.apache.kafka.common.protocol.types.Field;

public interface IPasswordEncryption {
    String encryptPassword(String rawPassword);
    Boolean matches(String rawPassword, String encryptedPassword);
}
