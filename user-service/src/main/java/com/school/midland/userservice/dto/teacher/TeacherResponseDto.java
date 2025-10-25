package com.school.midland.userservice.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDto {
    private String username;
    private String teacherCode;

    private String fullName;
//    private String password;

    private String personalEmail;
    private String phoneNumber;
    private String qualification;
    private String department;
    private LocalDate joinDate;
    private String designation;
    private String profileImage;
    private String schoolEmail;
    private String schoolCode;
}
