package com.dxs.auth.infrastructure.repository;

import com.dxs.auth.domain.user.UserCredentialsEntity;
import com.dxs.auth.domain.valuesobject.Email;
import com.dxs.auth.infrastructure.model.UserCredentialsDBModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository()
public interface UserCredentialsRepository extends JpaRepository<UserCredentialsDBModel, UUID> {
    UserCredentialsEntity findByEmail(Email email);
    UserCredentialsEntity save(UserCredentialsEntity userCredentialsEntity);

}
