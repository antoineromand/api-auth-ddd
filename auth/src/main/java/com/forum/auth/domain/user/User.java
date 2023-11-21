package com.forum.auth.domain.user;

import com.forum.auth.domain.valuesobject.Email;
import com.forum.auth.domain.valuesobject.Password;
import com.forum.auth.domain.valuesobject.ResetPasswordToken;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity(name = "users")
public class User {

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column
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
}
