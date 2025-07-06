package com.studentmanagement.test02.repo;

public interface SubjectAttendanceProjection {
    String getSubjectName();
    Long getTotalClasses();
    Long getPresentCount();

    default double getAttendancePercentage() {
        if (getTotalClasses() == 0) return 0.0;
        return (getPresentCount() * 100.0) / getTotalClasses();
    }
}