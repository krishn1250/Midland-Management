package com.school.midland.userservice.model;

/**
 * Represents the possible statuses for a student's attendance record.
 * Using an enum ensures that only valid statuses can be persisted.
 */
public enum AttendanceStatus {
    PRESENT,
    ABSENT,
}