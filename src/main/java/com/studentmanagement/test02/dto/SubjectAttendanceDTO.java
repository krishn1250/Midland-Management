package com.studentmanagement.test02.dto;

public class SubjectAttendanceDTO {
    private String subjectName;
    private Long totalClasses;
    private Long presentCount;
    private double attendancePercentage;
    
    // Constructors, getters, setters
    public SubjectAttendanceDTO() {}
    
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    
    public Long getTotalClasses() { return totalClasses; }
    public void setTotalClasses(Long totalClasses) { this.totalClasses = totalClasses; }
    
    public Long getPresentCount() { return presentCount; }
    public void setPresentCount(Long presentCount) { this.presentCount = presentCount; }
    
    public double getAttendancePercentage() { return attendancePercentage; }
    public void setAttendancePercentage(double attendancePercentage) { this.attendancePercentage = attendancePercentage; }
}
