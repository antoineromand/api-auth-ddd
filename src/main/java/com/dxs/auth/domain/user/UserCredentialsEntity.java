package com.dxs.auth.domain.user;

import com.dxs.auth.domain.valuesobject.Email;
import com.dxs.auth.domain.valuesobject.Password;
import com.dxs.auth.domain.valuesobject.ResetPasswordToken;

import java.util.Date;
import java.util.UUID;

public class UserCredentialsEntity {
    public enum Role {
        ADMIN, USER
    }

    public enum AccountStatus {
        ACTIVE, INACTIVE
    }

    private UUID id;

    private Email email;

    private Password password;

    private Role role = Role.USER;

    private AccountStatus accountStatus = AccountStatus.INACTIVE;

    private boolean isVerified;

    private Date createdAt;

    private Date updatedAt;

    private Date lastConnection;

    private ResetPasswordToken tokenResetPassword;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public ResetPasswordToken getTokenResetPassword() {
        return tokenResetPassword;
    }

    public void setTokenResetPassword(ResetPasswordToken tokenResetPassword) {
        this.tokenResetPassword = tokenResetPassword;
    }

    public void updateLastConnection() {
        this.lastConnection = new Date();
    }


}
