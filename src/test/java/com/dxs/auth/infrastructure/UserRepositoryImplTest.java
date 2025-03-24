package com.dxs.auth.infrastructure;

import com.dxs.auth.domain.repository.IUserRepository;
import com.dxs.auth.domain.user.UserCredentialsEntity;
import com.dxs.auth.domain.valuesobject.Email;
import com.dxs.auth.domain.valuesobject.Password;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserRepositoryImplTest {
    @Test
    void shouldRegisterUser() {
        IUserRepository IUserRepository = mock(IUserRepository.class);
        UserCredentialsEntity user = new UserCredentialsEntity();
        user.setEmail(new Email("user@exemple.com"));
        user.setPassword(new Password("efzeifjzioefjejrizjf,oizejfiozejfiozej"));
        IUserRepository.save(user);

        verify(IUserRepository).save(user);
    }

    @Test
    void shouldFindUserByEmail() {
        IUserRepository IUserRepository = mock(IUserRepository.class);
        UserCredentialsEntity user = new UserCredentialsEntity();
        user.setEmail(new Email("user@exemple.com"));
        user.setPassword(new Password("efzeifjzioefjejrizjf,oizejfiozejfiozej"));
        IUserRepository.findByEmail(user.getEmail());

        verify(IUserRepository).findByEmail(user.getEmail());
    }
}
