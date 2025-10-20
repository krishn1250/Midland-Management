package com.school.midland.userservice.controller;

import com.school.midland.commonlib.dtos.AttendanceDto;
import com.school.midland.userservice.dto.MarkAttendanceRequest;
import com.school.midland.userservice.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/midland/users/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Mark attendance
    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody MarkAttendanceRequest request) {
        attendanceService.markAttendance(request);
        return ResponseEntity.ok("Attendance marked successfully.");
    }

    // Get attendance for a teacher on a specific date
    @GetMapping("/teacher")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForTeacher(
            @RequestParam String teacherCode,
            @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getAttendanceForTeacher(teacherCode, localDate));
    }

    // Get attendance for a student in an academic year
    @GetMapping("/student")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForStudent(
            @RequestParam String admissionNumber,
            @RequestParam String academicYear) {
        return ResponseEntity.ok(attendanceService.getAttendanceForStudent(admissionNumber, academicYear));
    }

    // Get attendance for a class (grade + section) on a specific date
    @GetMapping("/class")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByClassAndDate(
            @RequestParam String gradeLevel,
            @RequestParam String section,
            @RequestParam String academicYear,
            @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getAttendanceByClassAndDate(gradeLevel, section, academicYear, localDate));
    }
}
