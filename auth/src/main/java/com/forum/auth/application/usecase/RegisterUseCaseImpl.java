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
    public void execute(String email, String password) throws EmailAlreadyExistsException {
        validateEmailNotTaken(email);
        String encryptedPassword = encryptPassword(password);
        UserCredentialsDTO userDto = new UserCredentialsDTO(email, encryptedPassword);
        UserCredentialsEntity user = convertToEntity(userDto);
        saveUser(user);
    }

    private void validateEmailNotTaken(String email) {
        if (userRepository.findByEmail(new Email(email)) != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    private String encryptPassword(String password) {
        return passwordEncryption.encryptPassword(password);
    }

    private UserCredentialsEntity convertToEntity(UserCredentialsDTO userDto) {
        return userCredentialsMapper.toEntity(userDto);
    }

    private void saveUser(UserCredentialsEntity user) {
        userRepository.save(user);
    }
}
