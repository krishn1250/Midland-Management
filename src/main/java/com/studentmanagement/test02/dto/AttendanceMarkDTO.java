package com.studentmanagement.test02.dto;

public class AttendanceMarkDTO {
    private String studentUuid;
    private String status; // PRESENT, ABSENT, LATE, EXCUSED
    private String remarks;
    
    // Constructors, getters, setters
    public AttendanceMarkDTO() {}
    
    public String getStudentUuid() { return studentUuid; }
    public void setStudentUuid(String studentUuid) { this.studentUuid = studentUuid; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}