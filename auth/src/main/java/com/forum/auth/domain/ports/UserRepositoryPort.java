package com.forum.auth.domain.ports;

import com.forum.auth.domain.user.UserCredentialsEntity;

public interface UserRepositoryPort {
    void save(UserCredentialsEntity user);
    UserCredentialsEntity findByEmail(String email);

}
