package com.school.midland.adminservice.client.service.attendance;

import com.school.midland.commonlib.dtos.AttendanceDto;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceServiceClient {
    public List<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date);
    public List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date);
    public List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear);
}
