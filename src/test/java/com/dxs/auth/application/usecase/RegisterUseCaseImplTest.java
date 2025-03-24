package com.dxs.auth.application.usecase;


import com.dxs.auth.application.mapper.UserCredentialsMapper;
import com.dxs.auth.domain.service.IPasswordEncryption;
import com.dxs.auth.domain.repository.IUserRepository;
import com.dxs.auth.domain.user.UserCredentialsEntity;
import com.dxs.auth.domain.valuesobject.Email;
import com.dxs.auth.application.exception.EmailAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RegisterUseCaseImplTest {
    private static IUserRepository mockIUserRepository;

    private static IPasswordEncryption mockPasswordEncryption;
    private static RegisterUseCaseImpl registerUseCaseImpl;

    @BeforeAll
    static void setUp() {
        mockIUserRepository = mock(IUserRepository.class);
        mockPasswordEncryption = mock(IPasswordEncryption.class);
        registerUseCaseImpl = new RegisterUseCaseImpl(mockIUserRepository, mockPasswordEncryption, new UserCredentialsMapper());
    }

    @Test
    public void shouldRegisterUser() {
        when(mockPasswordEncryption.encryptPassword(any())).thenReturn("encryptedPassword");
        String email = "user@example.com";
        String password = "12345678+Ab";
        when(mockIUserRepository.findByEmail(any())).thenReturn(null);
        registerUseCaseImpl.register(email, password);
        verify(mockIUserRepository, times(1)).save(any(UserCredentialsEntity.class));
        verify(mockIUserRepository, times(1)).findByEmail(any(Email.class));
        verify(mockPasswordEncryption, times(1)).encryptPassword(any(String.class));
    }

    @Test
    public void shouldNotRegisterUserWhenEmailAlreadyExists() {
        String email = "user@example.com";
        String password = "12345678+Ab";
        when(mockIUserRepository.findByEmail(any())).thenReturn(new UserCredentialsEntity());
        assertThrows(EmailAlreadyExistsException.class, () -> registerUseCaseImpl.register(email, password));
    }
}
