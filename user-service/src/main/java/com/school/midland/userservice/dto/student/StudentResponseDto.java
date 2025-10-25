package com.school.midland.userservice.dto.student;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class StudentResponseDto {

    private  String username;
    private String admissionNumber;
    private String rollNo;


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

    private String schoolCode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
