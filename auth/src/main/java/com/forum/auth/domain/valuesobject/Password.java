package com.forum.auth.domain.valuesobject;


import jakarta.persistence.Column;

public class Password {
    @Column()
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
