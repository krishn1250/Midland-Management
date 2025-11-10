package com.school.midland.user.service.attendance;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.attendance.AttendanceDto;
import com.school.midland.user.dto.attendance.MarkAttendanceRequest;
import com.school.midland.user.dto.attendance.MarkAttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    // ✅ Bulk mark attendance (creates or updates existing)
    MarkAttendanceResponse markAttendance(MarkAttendanceRequest request);

    // ✅ Get attendance for a specific teacher on a date (paged)
    PageResponse<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date, int page, int size);

    // ✅ Get all attendance records for a student for an academic year
    List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear);

    // ✅ Get all attendance for a class (grade + section) on a date
    List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date);

    // ✅ Edit an existing attendance entry
    AttendanceDto editAttendance(Long attendanceId, String status, String remarks, String updatedBy);
}
