package com.school.midland.user.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

    @Data
    public class TimetableDto {
        private Long id;
        private String gradeLevel;
        private String section;
        private String dayOfWeek;
        private Integer periodNumber;
        private LocalTime startTime;
        private LocalTime endTime;
        private String subjectCode;
        private String subjectName;
        private String teacherCode;
        private String roomNumber;
        private String academicYear;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }