package com.school.midland.commonlib.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {

    private Long id;

    private UUID studentUid;
    private String admissionNumber;

    private String gradeLevel;
    private String section;
    private String academicYear;

    private LocalDate attendanceDate;

    private String status; // Can be "PRESENT", "ABSENT", "LEAVE"

    private Integer periodNumber;

    private String subjectCode;
    private String subjectName;
    private String teacherCode;

    private String remarks;

    private Boolean isEditable;

    private String createdBy;
    private String updatedBy;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}