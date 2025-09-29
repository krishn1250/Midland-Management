package com.school.midland.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "concerns")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raisedByRole;       // Teacher / Parent / Student / Admin
    private String raisedByIdentifier; // Could be user id / email etc.

    @Column(nullable = false)
    private String admissionNumber;

    private String concernType;
    private String description;
    private String status;

    @Column(nullable = false)
    private String assignedToTeacherCode;

    private String response;

    private LocalDateTime createdAt = LocalDateTime.now();
}