package com.dxs.auth.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dxs.auth.core.entity.AbstractUser;
import com.dxs.auth.core.entity.RoleEnum;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends AbstractUser {
    @Id@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false)
    private RoleEnum role;
    @Column(nullable = false)
    private boolean active = true;
    @CreationTimestamp
    private LocalDateTime created_at;
}
