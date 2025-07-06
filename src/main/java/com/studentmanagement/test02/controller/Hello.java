package com.studentmanagement.test02.controller;

import com.studentmanagement.test02.dto.AttendanceDTO;
import com.studentmanagement.test02.service.AttendanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class Hello {
    private final AttendanceService attendanceService;

    public Hello(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/yo")
    public List<AttendanceDTO> hello(){
        return attendanceService.getStudentAttendanceForDate("student-001", LocalDate.of(2024,7,1));
    }
}
