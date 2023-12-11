package com.forum.auth.application.mapper;

import com.forum.auth.web.dto.UserCredentialsDTO;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.domain.valuesobject.Email;
import com.forum.auth.domain.valuesobject.Password;
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
