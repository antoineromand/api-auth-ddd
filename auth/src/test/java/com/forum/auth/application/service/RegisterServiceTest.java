package com.forum.auth.application.service;


import com.forum.auth.application.dto.UserCredentialsDTO;
import com.forum.auth.application.mapper.UserCredentialsMapper;
import com.forum.auth.domain.ports.PasswordEncryptionPort;
import com.forum.auth.domain.ports.UserRepositoryPort;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.infrastructure.errors.EmailAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegisterServiceTest {
    private static UserRepositoryPort mockUserRepository;

    private static PasswordEncryptionPort mockPasswordEncryption;
    private static RegisterService registerService;

    @BeforeAll
    static void setUp() {
        mockUserRepository = mock(UserRepositoryPort.class);
        mockPasswordEncryption = mock(PasswordEncryptionPort.class);
        registerService = new RegisterService(mockUserRepository, mockPasswordEncryption, new UserCredentialsMapper());
    }

    @Test
    public void shouldRegisterUser() {
        UserCredentialsDTO userDto = new UserCredentialsDTO("user@example.com", "12345678+Ab");
        when(mockPasswordEncryption.encryptPassword(any())).thenReturn("encryptedPassword");
        registerService.register(userDto);
        verify(mockUserRepository, times(1)).save(any(UserCredentialsEntity.class));
        verify(mockUserRepository, times(1)).findByEmail(any(String.class));
        verify(mockPasswordEncryption, times(1)).encryptPassword(any(String.class));
    }

    @Test
    public void shouldNotRegisterUserWhenEmailAlreadyExists() {
        UserCredentialsDTO userDto = new UserCredentialsDTO("user@example.com", "12345678+Ab");
        when(mockUserRepository.findByEmail(any())).thenReturn(new UserCredentialsEntity());
        assertThrows(EmailAlreadyExistsException.class, () -> {
            registerService.register(userDto);
        });
    }
}
