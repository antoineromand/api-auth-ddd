package com.forum.auth.application.dto;

import com.forum.auth.application.validators.EmailValidator;
import com.forum.auth.application.validators.PasswordValidator;

public class UserCredentialsDTO {
    private String email;

    private String password;

    public UserCredentialsDTO(String email, String password) {
        EmailValidator.validate(email);
        PasswordValidator.validate(password);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
