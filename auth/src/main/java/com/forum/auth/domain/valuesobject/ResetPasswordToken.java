package com.forum.auth.domain.valuesobject;

import jakarta.persistence.Column;

public class ResetPasswordToken {
    @Column()
    private final String resetPasswordToken;

    public ResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getToken() {
        return resetPasswordToken;
    }
}
