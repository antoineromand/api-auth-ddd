package com.forum.auth.application.usecase;

import com.forum.auth.web.dto.UserCredentialsDTO;
import com.forum.auth.application.mapper.UserCredentialsMapper;
import com.forum.auth.domain.service.IPasswordEncryption;
import com.forum.auth.domain.repository.IUserRepository;
import com.forum.auth.domain.usecase.RegisterUseCase;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.domain.valuesobject.Email;
import com.forum.auth.application.exception.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCaseImpl implements RegisterUseCase {
    private final IUserRepository userRepository;
    private final IPasswordEncryption passwordEncryption;
    private final UserCredentialsMapper userCredentialsMapper;

    @Autowired
    public RegisterUseCaseImpl(IUserRepository userRepository, IPasswordEncryption passwordEncryption, UserCredentialsMapper userCredentialsMapper) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
        this.userCredentialsMapper = userCredentialsMapper;
    }

    @Override
    public void register(String email, String password) throws EmailAlreadyExistsException {
        UserCredentialsDTO userDto = new UserCredentialsDTO(email, password);
        String encryptedPassword = passwordEncryption.encryptPassword(userDto.getPassword());
        UserCredentialsEntity userCredentialsEntity = userRepository.findByEmail(new Email(userDto.getEmail()));
        if (userCredentialsEntity != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        userDto.setPassword(encryptedPassword);
        UserCredentialsEntity user = userCredentialsMapper.toEntity(userDto);
        userRepository.save(user);
    }
}
