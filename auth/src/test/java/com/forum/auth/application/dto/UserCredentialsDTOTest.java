package com.forum.auth.application.dto;

import com.forum.auth.application.errors.BlankException;
import com.forum.auth.application.errors.EmailInvalidException;
import com.forum.auth.application.errors.EmptyException;
import com.forum.auth.application.errors.PasswordExceptions.PasswordFormInvalidException;
import com.forum.auth.application.errors.PasswordExceptions.PasswordLengthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialsDTOTest {
    @Test
    void shouldCreateEmailWhenValid() {
        String emailStr = "test@example.com";
        UserCredentialsDTO dto = new UserCredentialsDTO(emailStr, "123456A+y4");
        assertEquals(emailStr, dto.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenEmailInvalid() {
        String invalidEmail = "invalid_email.com";
        assertThrows(EmailInvalidException.class, () -> new UserCredentialsDTO(invalidEmail, "123456A+y4"));
    }

    @Test
    void shouldThrowExceptionWhenEmailNull() {
        String invalidEmail = null;
        assertThrows(NullPointerException.class, () -> new UserCredentialsDTO(invalidEmail, "123456A+y4"));
    }


    @Test
    public void shouldCreatePassword() {
        String emailStr = "test@example.com";
        UserCredentialsDTO dto = new UserCredentialsDTO(emailStr, "12B4567a+33");
        assertEquals("12B4567a+33", dto.getPassword());
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsTooShort() {
        String emailStr = "test@example.com";
        assertThrows(PasswordLengthException.class, () -> new UserCredentialsDTO(emailStr,"1AB4a+6"), "Password must be at least 8 characters long");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsNull() {
        String emailStr = "test@example.com";

        assertThrows(NullPointerException.class, () -> new UserCredentialsDTO(emailStr,null), "Password must not be null");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsEmpty() {
        String emailStr = "test@example.com";

        assertThrows(EmptyException.class, () -> new UserCredentialsDTO(emailStr,""), "Password must not be empty");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsBlank() {
        String emailStr = "test@example.com";

        assertThrows(BlankException.class, () -> new UserCredentialsDTO(emailStr,"    "), "Password must not be blank");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordDoesNotContainAtLeastOneDigit() {
        String emailStr = "test@example.com";

        assertThrows(PasswordFormInvalidException.class, () -> new UserCredentialsDTO(emailStr,"Abcdefgh+"), "Password must contain at least one digit");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordDoesNotContainAtLeastOneUpperCaseLetter() {
        String emailStr = "test@example.com";

        assertThrows(PasswordFormInvalidException.class, () -> new UserCredentialsDTO(emailStr, "abcdefgh4+"), "Password must contain at least one upper case letter");
    }


}
