package com.studentmanagement.test02.dto;

public class AttendanceStatsDTO {
    private String studentUuid;
    private Long totalClasses;
    private Long presentCount;
    private Long lateCount;
    private Long absentCount;
    private double attendancePercentage;
    
    // Constructors, getters, setters
    public AttendanceStatsDTO() {}
    
    public String getStudentUuid() { return studentUuid; }
    public void setStudentUuid(String studentUuid) { this.studentUuid = studentUuid; }
    
    public Long getTotalClasses() { return totalClasses; }
    public void setTotalClasses(Long totalClasses) { this.totalClasses = totalClasses; }
    
    public Long getPresentCount() { return presentCount; }
    public void setPresentCount(Long presentCount) { this.presentCount = presentCount; }
    
    public Long getLateCount() { return lateCount; }
    public void setLateCount(Long lateCount) { this.lateCount = lateCount; }
    
    public Long getAbsentCount() { return absentCount; }
    public void setAbsentCount(Long absentCount) { this.absentCount = absentCount; }
    
    public double getAttendancePercentage() { return attendancePercentage; }
    public void setAttendancePercentage(double attendancePercentage) { this.attendancePercentage = attendancePercentage; }
}
