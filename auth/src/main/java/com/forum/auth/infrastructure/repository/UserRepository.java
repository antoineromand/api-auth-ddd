package com.forum.auth.infrastructure.repository;

import com.forum.auth.domain.user.UserCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository()
public interface UserRepository extends JpaRepository<UserCredentialsEntity, UUID> {
    UserCredentialsEntity findByEmail(String email);
    UserCredentialsEntity save(UserCredentialsEntity userCredentialsEntity);

}
