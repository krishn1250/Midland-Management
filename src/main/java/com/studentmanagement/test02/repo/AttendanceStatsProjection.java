package com.studentmanagement.test02.repo;

// Projection interfaces for complex queries
public interface AttendanceStatsProjection {
    Long getTotalClasses();
    Long getPresentCount();
    Long getLateCount();
    Long getAbsentCount();

    default double getAttendancePercentage() {
        if (getTotalClasses() == 0) return 0.0;
        return (getPresentCount() * 100.0) / getTotalClasses();
    }
}