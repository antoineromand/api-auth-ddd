package com.dxs.auth.infrastructure.repository;

import com.dxs.auth.infrastructure.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserModelRepository extends JpaRepository<UUID, UserModel> {
}
