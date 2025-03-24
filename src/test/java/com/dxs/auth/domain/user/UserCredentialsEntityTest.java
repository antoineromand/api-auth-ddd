package com.dxs.auth.domain.user;
import com.dxs.auth.domain.valuesobject.Email;
import com.dxs.auth.domain.valuesobject.Password;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserCredentialsEntityTest {
    @Test
    void emailShouldBeSetAndRetrievedCorrectly() {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setEmail(new Email("admin@admin.com"));
        assertEquals("admin@admin.com", userCredentialsEntity.getEmail().getValue());
    }

    @Test
    void passwordShouldBeSetAndRetrievedCorrectly() {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setPassword(new Password("F4k3P4ssw0rd++"));
        assertEquals("F4k3P4ssw0rd++", userCredentialsEntity.getPassword().getValue());
    }

    @Test
    void roleShouldBeSetAndRetrievedCorrectly() {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setRole(UserCredentialsEntity.Role.ADMIN);
        assertEquals(UserCredentialsEntity.Role.ADMIN, userCredentialsEntity.getRole());
    }

    @Test
    void accountStatusShouldBeSetAndRetrievedCorrectly() {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setAccountStatus(UserCredentialsEntity.AccountStatus.ACTIVE);
        assertEquals(UserCredentialsEntity.AccountStatus.ACTIVE, userCredentialsEntity.getAccountStatus());
    }

    @Test
    void isVerifiedShouldBeSetAndRetrievedCorrectly() {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setVerified(true);
        assertTrue(userCredentialsEntity.isVerified());
    }


}
