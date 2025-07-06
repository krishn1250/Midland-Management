package com.studentmanagement.test02.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDTO {
    private Long attendanceId;
    private String studentName;
    private String subjectName;
    private LocalDate date;
    private String status;
    private LocalDateTime timestampMarked;
    private String remarks;

    // Constructors, getters, setters
    public AttendanceDTO() {}

    // Getters and setters
    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getTimestampMarked() { return timestampMarked; }
    public void setTimestampMarked(LocalDateTime timestampMarked) { this.timestampMarked = timestampMarked; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
