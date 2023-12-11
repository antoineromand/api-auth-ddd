package com.forum.auth.domain.user;

import com.forum.auth.domain.valuesobject.Email;
import com.forum.auth.domain.valuesobject.Password;
import com.forum.auth.domain.valuesobject.ResetPasswordToken;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity(name = "users_credentials")
public class UserCredentialsEntity {



    public enum Role {
        ADMIN, USER
    }

    public enum AccountStatus {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue
    private UUID id;

    @Embedded()
    private Email email;

    @Embedded()
    private Password password;

    @Column(columnDefinition = "varchar(60) default 'USER'")
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Column(columnDefinition = "varchar(60) default 'INACTIVE'")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.INACTIVE;

    @Column(name = "is_verified", columnDefinition = "boolean default false")
    private boolean isVerified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    @Column(name = "last_connection")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnection;

    @Embedded()
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

    // Update date automatically when the entity is persisted
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
    // Update date automatically when the entity is updated
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
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
