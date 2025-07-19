package com.school.midland.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private UUID studentUid;
    private  String username;
    private String admissionNumber;
    private String rollNo;
    private  String password;

    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private String nationality;
    private String motherTongue;
    private String languagePreference;

    private String gradeLevel;
    private String section;
    private String academicYear;
    private LocalDate admissionDate;
    private String status;

    private String profileImage;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    private String phoneNumber;
    private String schoolEmail;
    private String personalEmail;

    private String guardianName;
    private String guardianRelation;
    private String guardianContact;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
