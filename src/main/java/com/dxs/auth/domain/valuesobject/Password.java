package com.dxs.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class Password {
    @Column(name = "password")
    private String value;

    public Password() { }

    public Password(String password) {
        validateEncryptedPassword(password);
        this.value = password;
    }

    public String getValue() {
        return value;
    }

    private void validateEncryptedPassword(String encryptedPassword) {
        if (encryptedPassword == null || !encryptedPassword.matches("\\$2[aby]\\$\\d{2}\\$[./0-9A-Za-z]{53}")) {
            throw new IllegalArgumentException("Invalid BCrypt password format");
        }
    }

}
