package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.MarkAttendanceRequest;
import com.school.midland.userservice.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/midland/users/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody MarkAttendanceRequest request) {
        attendanceService.markAttendance(request);
        return ResponseEntity.ok("Attendance marked successfully.");
    }
}