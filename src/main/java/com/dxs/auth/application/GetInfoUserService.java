package com.dxs.auth.application;

import com.dxs.auth.core.exceptions.AuthenticationFailedException;
import com.dxs.auth.domain.User;
import com.dxs.auth.infrastructure.model.UserModel;
import com.dxs.auth.infrastructure.repository.UserModelRepository;

import java.util.Optional;
import java.util.UUID;

public class GetInfoUserService {
    private UserModelRepository repository;

    public User getInfo(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID provided is not valid");
        }
        Optional<UserModel> query = repository.findById(userId);
        if (query.isEmpty()) {
            throw new AuthenticationFailedException("User not found.");
        }
        UserModel foundedUser = query.get();
        return new User(foundedUser.getId(),
                foundedUser.getEmail(),
                "********", // password masked
                foundedUser.getRole(),
                foundedUser.isActive(),
                foundedUser.getCreated_at());
    }
}
