package com.school.midland.user.controller;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.attendance.*;
import com.school.midland.user.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<MarkAttendanceResponse> markAttendance(@RequestBody MarkAttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.markAttendance(request));
    }

    @GetMapping("/teacher")
    public ResponseEntity<PageResponse<AttendanceDto>> getAttendanceForTeacher(
            @RequestParam String teacherCode,
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<AttendanceDto> result = attendanceService.getAttendanceForTeacher(teacherCode, date, page, size);
        PageResponse<AttendanceDto> response = new PageResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForStudent(
            @RequestParam String admissionNumber,
            @RequestParam String academicYear) {
        return ResponseEntity.ok(attendanceService.getAttendanceForStudent(admissionNumber, academicYear));
    }

    @GetMapping("/class")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByClassAndDate(
            @RequestParam String gradeLevel,
            @RequestParam String section,
            @RequestParam String academicYear,
            @RequestParam LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByClassAndDate(gradeLevel, section, academicYear, date));
    }

    @PutMapping("/edit/{attendanceId}")
    public ResponseEntity<AttendanceDto> editAttendance(
            @PathVariable Long attendanceId,
            @RequestParam String status,
            @RequestParam(required = false) String remarks,
            @RequestParam String updatedBy) {
        return ResponseEntity.ok(attendanceService.editAttendance(attendanceId, status, remarks, updatedBy));
    }
}
