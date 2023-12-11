package com.forum.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class Password {
    @Column(name = "password")
    private String value;

    public Password() {}

    public Password(String password) {
        this.value = password;
    }

    public String getValue() {
        return value;
    }

}
