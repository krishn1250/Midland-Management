package com.school.midland.userservice.service.attendance;

import com.school.midland.commonlib.dtos.AttendanceDto;
import com.school.midland.userservice.dto.MarkAttendanceRequest;
import com.school.midland.userservice.dto.MarkAttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    void markAttendance(MarkAttendanceRequest request);

    List<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date);

    List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear);

    List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date);
}
