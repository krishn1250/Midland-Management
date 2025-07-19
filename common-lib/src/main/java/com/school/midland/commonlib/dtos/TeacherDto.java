package com.school.midland.commonlib.dtos;

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
public class TeacherDto {
    private String username;
    private UUID   teacherUid;
    private String teacherCode;
    private String firstName;
    private String password;
    private String lastName;
    private String personalEmail;
    private String phoneNumber;
    private String qualification;
    private String department;
    private LocalDate joinDate;
    private String designation;
    private String profileImage;
    private String schoolEmail;
}
