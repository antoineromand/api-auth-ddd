package com.forum.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class Password {
    @Column()
    private final String value;

    public Password(String password) {
        this.value = password;
    }

    public String getValue() {
        return value;
    }

}
