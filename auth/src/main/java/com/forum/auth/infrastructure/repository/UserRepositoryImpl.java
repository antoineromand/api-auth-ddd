package com.forum.auth.infrastructure.repository;

import com.forum.auth.domain.ports.IUserRepository;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.domain.valuesobject.Email;
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
