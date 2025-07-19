package com.school.midland.userservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_uid", nullable = false, unique = true)
    private UUID studentUid;

    @Column(name = "admission_number", nullable = false, unique = true)
    private String admissionNumber;

    @Column(name = "roll_no", unique = true)
    private String rollNo;

    @Column(name = "username", unique = true,nullable = false)
    private String  username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "mother_tongue")
    private String motherTongue;

    @Column(name = "language_preference")
    private String languagePreference;

    @Column(name = "grade_level", nullable = false)
    private String gradeLevel;

    @Column(name = "section")
    private String section;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "profile_image", columnDefinition = "TEXT")
    private String profileImage;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "school_email", nullable = false, unique = true)
    private String schoolEmail;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "guardian_relation")
    private String guardianRelation;

    @Column(name = "guardian_contact")
    private String guardianContact;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
