package com.school.midland.adminservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(unique = true, insertable = false, updatable = false)
    private UUID adminUid;

    @Column(nullable = false, unique = true)
    private UUID userUid; // Foreign key to users(user_uid)

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;


    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String designation;
    private String profileImage;
    private String notes;

    @Column(nullable = false)
    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        if (adminUid == null) adminUid = UUID.randomUUID();
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
