package com.dxs.auth.infrastructure.repository;

import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.infrastructure.model.UserModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserModelRepository implements AbstractUserRepository<UserModel> {
    private final UserModelRepository userModelRepository;

    public JPAUserModelRepository(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }
    @Override
    public Optional<UserModel> findByEmail(String email) {
        return this.userModelRepository.findByEmail(email);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return this.userModelRepository.save(userModel);
    }
}
