package com.forum.auth.infrastructure.repository;

import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.domain.valuesobject.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository()
public interface UserCredentialsRepository extends JpaRepository<UserCredentialsEntity, UUID> {
    UserCredentialsEntity findByEmail(Email email);
    UserCredentialsEntity save(UserCredentialsEntity userCredentialsEntity);

}
