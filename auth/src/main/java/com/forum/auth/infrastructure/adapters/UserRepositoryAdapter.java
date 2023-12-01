package com.forum.auth.infrastructure.adapters;

import com.forum.auth.domain.ports.UserRepositoryPort;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository jpaRepository;

    @Autowired
    public UserRepositoryAdapter(UserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(UserCredentialsEntity user) {
        jpaRepository.save(user);
    }

    @Override
    public UserCredentialsEntity findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

}
