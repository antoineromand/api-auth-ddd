package com.forum.auth.infrastructure;

import com.forum.auth.domain.ports.UserRepositoryPort;
import com.forum.auth.domain.user.UserCredentialsEntity;
import com.forum.auth.domain.valuesobject.Email;
import com.forum.auth.domain.valuesobject.Password;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserRepositoryAdapterTest {
    @Test
    void shouldRegisterUser() {
        UserRepositoryPort userRepository = mock(UserRepositoryPort.class);
        UserCredentialsEntity user = new UserCredentialsEntity();
        user.setEmail(new Email("user@exemple.com"));
        user.setPassword(new Password("efzeifjzioefjejrizjf,oizejfiozejfiozej"));
        userRepository.save(user);

        verify(userRepository).save(user);
    }

    @Test
    void shouldFindUserByEmail() {
        UserRepositoryPort userRepository = mock(UserRepositoryPort.class);
        UserCredentialsEntity user = new UserCredentialsEntity();
        user.setEmail(new Email("user@exemple.com"));
        user.setPassword(new Password("efzeifjzioefjejrizjf,oizejfiozejfiozej"));
        userRepository.findByEmail(user.getEmail().getValue());

        verify(userRepository).findByEmail(user.getEmail().getValue());
    }
}
