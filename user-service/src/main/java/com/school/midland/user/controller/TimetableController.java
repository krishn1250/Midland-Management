package com.school.midland.user.controller;


import com.school.midland.user.dto.TimetableDto;
import com.school.midland.user.service.timetable.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/users/timetables")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping("/create")
    public ResponseEntity<TimetableDto> createTimetable(@RequestBody TimetableDto timetableDto) {
        return ResponseEntity.ok(timetableService.createTimetable(timetableDto));
    }

    @PostMapping("/list/create")
    public ResponseEntity<List<TimetableDto>> createTimetables(@RequestBody List<TimetableDto> timetableDtos) {
        return ResponseEntity.ok(timetableService.createTimetables(timetableDtos));
    }

    @PutMapping("/update/{timetableCode}")
    public ResponseEntity<TimetableDto> updateTimetable(
            @PathVariable String timetableCode,
            @RequestBody TimetableDto timetableDto) {
        return ResponseEntity.ok(timetableService.updateTimetable(timetableCode, timetableDto));
    }

    @GetMapping("/teacher/{teacherCode}")
    public ResponseEntity<List<TimetableDto>> getTeacherTimetable(@PathVariable String teacherCode) {
        return ResponseEntity.ok(timetableService.getTeacherTimetable(teacherCode));
    }

    @GetMapping("/class")
    public ResponseEntity<List<TimetableDto>> getClassTimetable(
            @RequestParam String gradeLevel,
            @RequestParam String section,
            @RequestParam String dayOfWeek) {
        return ResponseEntity.ok(timetableService.getTimetableForClass(gradeLevel, section, dayOfWeek));
    }

    @GetMapping("/active")
    public ResponseEntity<List<TimetableDto>> getAllActiveTimetables() {
        return ResponseEntity.ok(timetableService.getAllActiveTimetables());
    }

    @PatchMapping("/deactivate/{timetableCode}")
    public ResponseEntity<String> deactivateTimetable(@PathVariable String timetableCode) {
        return ResponseEntity.ok(timetableService.deactivateTimetable(timetableCode));
    }
}
