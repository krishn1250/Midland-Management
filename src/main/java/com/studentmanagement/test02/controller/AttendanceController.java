package com.studentmanagement.test02.controller;

import com.studentmanagement.test02.dto.AttendanceDTO;
import com.studentmanagement.test02.dto.AttendanceStatsDTO;
import com.studentmanagement.test02.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("{studentId}")
    public ResponseEntity<AttendanceStatsDTO> getAttendanceByStudentId(@PathVariable String studentId) {
        // This method will handle the request to get attendance by student ID
        // You can implement the logic to fetch attendance data here
        // For now, returning a placeholder response
        AttendanceStatsDTO attendanceStatsDTO = attendanceService.getStudentAttendanceStats(studentId);

        return ResponseEntity.ok().body(attendanceStatsDTO);
    }


}
