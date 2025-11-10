package com.school.midland.user.dto.attendance;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
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

    private List<PerStudentResult> results;


}