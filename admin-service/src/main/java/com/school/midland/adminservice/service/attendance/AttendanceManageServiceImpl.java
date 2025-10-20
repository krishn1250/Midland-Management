package com.school.midland.adminservice.service.attendance;

import com.school.midland.adminservice.client.service.attendance.AttendanceServiceClient;
import com.school.midland.commonlib.dtos.AttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceManageServiceImpl implements AttendanceManageService {

    private final AttendanceServiceClient attendanceClient;

    @Override
    public List<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date) {
        return attendanceClient.getAttendanceForTeacher(teacherCode, date);
    }

    @Override
    public List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear) {
        return attendanceClient.getAttendanceForStudent(admissionNumber, academicYear);
    }

    @Override
    public List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date) {
        return attendanceClient.getAttendanceByClassAndDate(gradeLevel, section, academicYear, date);
    }
}
