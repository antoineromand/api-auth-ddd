package com.dxs.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class Email {

    @Column(name = "email")
    private  String value;

    public Email() { }

    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
