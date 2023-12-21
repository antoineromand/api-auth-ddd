package com.forum.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class ResetPasswordToken {
    @Column()
    private  String resetPasswordToken;

    public ResetPasswordToken() {}

    public ResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getValue() {
        return resetPasswordToken;
    }
}
