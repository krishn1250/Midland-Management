package com.school.midland.admin.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "admin_uid", unique = true, nullable = false, updatable = false)

    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID adminUid;

    @Column(name = "user_uid", nullable = false, unique = true)
    private UUID userUid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "designation")
    private String designation = "System Admin";

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "school_code", nullable = false)
    private String schoolCode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}

