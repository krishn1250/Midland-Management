package com.school.midland.userservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MarkAttendanceRequest {
    private String gradeLevel;
    private String section;
    private String academicYear;
    private LocalDate attendanceDate;
    private Integer periodNumber;
    private String subjectCode;
    private String subjectName;
    private String teacherCode;
    private String createdBy;

    private List<StudentAttendanceEntry> attendanceList;


}