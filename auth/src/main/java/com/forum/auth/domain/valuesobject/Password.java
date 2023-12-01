package com.forum.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class Password {
    @Column()
    private String value;

    public Password(String password) {
        this.value = password;
    }

    public String getValue() {
        return value;
    }

}
