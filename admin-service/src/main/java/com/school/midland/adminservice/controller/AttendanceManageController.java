package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.service.attendance.AttendanceManageService;
import com.school.midland.commonlib.dtos.AttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/midland/admin/attendance")
@RequiredArgsConstructor
public class AttendanceManageController {

    private final AttendanceManageService attendanceService;

    // View attendance for teacher
    @GetMapping("/teacher")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForTeacher(
            @RequestParam String teacherCode,
            @RequestParam LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceForTeacher(teacherCode, date));
    }

    // View attendance for student
    @GetMapping("/student")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForStudent(
            @RequestParam String admissionNumber,
            @RequestParam String academicYear) {
        return ResponseEntity.ok(attendanceService.getAttendanceForStudent(admissionNumber, academicYear));
    }

    // View attendance by class
    @GetMapping("/class")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByClass(
            @RequestParam String gradeLevel,
            @RequestParam String section,
            @RequestParam String academicYear,
            @RequestParam LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByClassAndDate(gradeLevel, section, academicYear, date));
    }
}
