package com.dxs.auth.domain.repository;

import com.dxs.auth.domain.user.UserCredentialsEntity;
import com.dxs.auth.domain.valuesobject.Email;

public interface IUserRepository {
    UserCredentialsEntity save(UserCredentialsEntity user);
    UserCredentialsEntity findByEmail(Email email);

}
