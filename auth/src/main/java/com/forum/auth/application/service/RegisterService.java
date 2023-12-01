package com.forum.auth.application.service;

import com.forum.auth.application.dto.UserCredentialsDTO;
import com.forum.auth.application.mapper.UserCredentialsMapper;
import com.forum.auth.domain.ports.PasswordEncryptionPort;
import com.forum.auth.domain.ports.UserRepositoryPort;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.infrastructure.errors.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service()
public class RegisterService {
    private final UserRepositoryPort userRepository;
    private final PasswordEncryptionPort passwordEncryption;
    private final UserCredentialsMapper userCredentialsMapper;

    public RegisterService(UserRepositoryPort userRepository, PasswordEncryptionPort passwordEncryption, UserCredentialsMapper userCredentialsMapper) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
        this.userCredentialsMapper = userCredentialsMapper;
    }

    public void register(UserCredentialsDTO userDto) {
        String encryptedPassword = passwordEncryption.encryptPassword(userDto.getPassword());
        UserCredentialsEntity userCredentialsEntity = userRepository.findByEmail(userDto.getEmail());
        if (userCredentialsEntity != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        userDto.setPassword(encryptedPassword);
        UserCredentialsEntity user = userCredentialsMapper.toEntity(userDto);
        userRepository.save(user);
    }
}
