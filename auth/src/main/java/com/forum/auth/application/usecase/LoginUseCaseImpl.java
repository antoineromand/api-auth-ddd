package com.forum.auth.application.usecase;

import com.forum.auth.application.exception.InactiveUserException;
import com.forum.auth.application.exception.IncorrectPasswordException;
import com.forum.auth.application.exception.UserNotFoundException;
import com.forum.auth.domain.repository.IUserRepository;
import com.forum.auth.domain.service.IPasswordEncryption;
import com.forum.auth.domain.usecase.LoginUseCase;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.domain.valuesobject.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    private IPasswordEncryption passwordEncryption;

    private IUserRepository userRepository;

    @Autowired
    public LoginUseCaseImpl(IPasswordEncryption passwordEncryption, IUserRepository userRepository) {
        this.passwordEncryption = passwordEncryption;
        this.userRepository = userRepository;
    }
    @Override
    public UserCredentialsEntity login(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        UserCredentialsEntity user = validateUserExist(email);
        validatePassword(password, user.getPassword().getValue());
        validateAccountStatus(user);
        return user;
    }

    private UserCredentialsEntity validateUserExist(String email) {
        if (userRepository.findByEmail(new Email(email)) == null) {
            throw new UserNotFoundException("Email already exists");
        } else {
            return userRepository.findByEmail(new Email(email));
        }
    }

    private void validatePassword(String password, String encryptedPassword) {
        if(!passwordEncryption.matches(password, encryptedPassword))
            throw new IncorrectPasswordException("Incorrect password");
    }

    private void validateAccountStatus(UserCredentialsEntity user) {
        if(user.getAccountStatus().equals(UserCredentialsEntity.AccountStatus.INACTIVE))
            throw new InactiveUserException("User must activate account first");
    }


}
