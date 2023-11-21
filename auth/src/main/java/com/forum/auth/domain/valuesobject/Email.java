package com.forum.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class Email {
    @Column()
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
