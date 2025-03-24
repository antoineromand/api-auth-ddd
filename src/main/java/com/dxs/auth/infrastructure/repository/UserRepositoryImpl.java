package com.dxs.auth.infrastructure.repository;

import com.dxs.auth.domain.repository.IUserRepository;
import com.dxs.auth.domain.user.UserCredentialsEntity;
import com.dxs.auth.domain.valuesobject.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements IUserRepository {

    private final UserCredentialsRepository repository;

    @Autowired
    public UserRepositoryImpl(UserCredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserCredentialsEntity save(UserCredentialsEntity user) {
        return repository.save(user);
    }

    @Override
    public UserCredentialsEntity findByEmail(Email email) {
        return repository.findByEmail(email);
    }

}
