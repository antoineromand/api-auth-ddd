package com.dxs.auth.infrastructure.model;

import com.dxs.auth.core.entity.IUserFactory;
import com.dxs.auth.core.entity.RoleEnum;
import com.dxs.auth.web.dto.auth.register.RegisterInputDTO;
import org.springframework.stereotype.Component;

@Component
public class UserModelFactory implements IUserFactory<UserModel, RegisterInputDTO> {

    @Override
    public UserModel createUser(RegisterInputDTO registerDTO, String encryptedPassword) {
        UserModel user = new UserModel();
        user.setRole(RoleEnum.REGULAR);
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encryptedPassword);
        return user;
    }
}
