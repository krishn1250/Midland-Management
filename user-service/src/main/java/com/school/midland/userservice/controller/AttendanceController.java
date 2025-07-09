// --- File: controller/AttendanceController.java ---
package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.AttendanceRequestDTO;
import com.school.midland.userservice.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Void> markAttendance(@Valid @RequestBody AttendanceRequestDTO request) {
        attendanceService.markAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}