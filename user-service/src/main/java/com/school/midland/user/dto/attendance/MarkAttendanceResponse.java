package com.school.midland.user.dto.attendance;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MarkAttendanceResponse {
    private String gradeLevel;
    private String section;
    private String academicYear;
    private LocalDate attendanceDate;
    private Integer periodNumber;
    private String subjectName;
    private String teacherCode;
    private String createdBy;

    private List<StudentAttendanceEntry> attendanceList;
}
