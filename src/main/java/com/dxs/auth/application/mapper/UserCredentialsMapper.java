package com.dxs.auth.application.mapper;

import com.dxs.auth.web.dto.UserCredentialsDTO;
import com.dxs.auth.domain.user.UserCredentialsEntity;
import com.dxs.auth.domain.valuesobject.Email;
import com.dxs.auth.domain.valuesobject.Password;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsMapper {
    public UserCredentialsEntity toEntity(UserCredentialsDTO userCredentialsDTO) {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setPassword(new Password(userCredentialsDTO.getPassword()));
        userCredentialsEntity.setEmail(new Email(userCredentialsDTO.getEmail()));
        return userCredentialsEntity;
    }
}
