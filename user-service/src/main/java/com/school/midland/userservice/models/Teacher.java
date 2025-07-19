package com.school.midland.userservice.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "teacher")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false, unique = true)
    private UUID teacherUid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String teacherCode;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true, name = "school_email")
    private String schoolEmail;

    @Column(unique = true, name = "personal_email")
    private String personalEmail;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String qualification;

    private String department;

    private LocalDate joinDate;

    private String designation;

    private String profileImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
