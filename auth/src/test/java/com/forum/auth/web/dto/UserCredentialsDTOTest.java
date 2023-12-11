package com.forum.auth.web.dto;

import com.forum.auth.web.dto.errors.BlankException;
import com.forum.auth.web.dto.errors.EmailInvalidException;
import com.forum.auth.web.dto.errors.EmptyException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordFormInvalidException;
import com.forum.auth.web.dto.errors.PasswordExceptions.PasswordLengthException;
import com.forum.auth.web.dto.validators.EmailValidator;
import com.forum.auth.web.dto.validators.PasswordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialsDTOTest {
    @Test
    void shouldCreateEmailWhenValid() {
        String emailStr = "test@example.com";
        UserCredentialsDTO dto = new UserCredentialsDTO(emailStr, "123456A+y4");
        EmailValidator.validate(dto.getEmail());
        assertEquals(emailStr, dto.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenEmailInvalid() {
        String invalidEmail = "invalid_email.com";
        assertThrows(EmailInvalidException.class, () -> EmailValidator.validate(invalidEmail));
    }

    @Test
    void shouldThrowExceptionWhenEmailNull() {
        String invalidEmail = null;
        assertThrows(NullPointerException.class, () -> EmailValidator.validate(invalidEmail));
    }


    @Test
    public void shouldCreatePassword() {
        String emailStr = "test@example.com";
        UserCredentialsDTO dto = new UserCredentialsDTO(emailStr, "12B4567a+33");
        PasswordValidator.validate(dto.getPassword());
        assertEquals("12B4567a+33", dto.getPassword());
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsTooShort() {
        String emailStr = "test@example.com";
        assertThrows(PasswordLengthException.class, () -> PasswordValidator.validate("1AB4a+6"), "Password must be at least 8 characters long");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsNull() {
        assertThrows(NullPointerException.class, () -> PasswordValidator.validate(null), "Password must not be null");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsEmpty() {
        assertThrows(EmptyException.class, () -> PasswordValidator.validate(""), "Password must not be empty");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsBlank() {
        assertThrows(BlankException.class, () -> PasswordValidator.validate("    "), "Password must not be blank");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordDoesNotContainAtLeastOneDigit() {
        assertThrows(PasswordFormInvalidException.class, () -> PasswordValidator.validate("Abcdefgh+"), "Password must contain at least one digit");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordDoesNotContainAtLeastOneUpperCaseLetter() {
        String emailStr = "test@example.com";

        assertThrows(PasswordFormInvalidException.class, () -> PasswordValidator.validate("abcdefgh4+"), "Password must contain at least one upper case letter");
    }


}
