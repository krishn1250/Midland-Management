package com.school.midland.auth.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_uid", unique = true, nullable = false, updatable = false)
//    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userUid;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "associated_identifier", unique = true)
    private String associatedIdentifier;

    @Column(name = "login_identifier")
    private String loginIdentifier;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;
}
