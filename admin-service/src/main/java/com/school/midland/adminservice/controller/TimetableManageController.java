package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.service.timetable.TimetableManageService;
import com.school.midland.commonlib.dtos.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/admin/timetables")
@RequiredArgsConstructor
public class TimetableManageController {

    private final TimetableManageService timetableManager;

    @PostMapping("/create")
    public ResponseEntity<List<TimetableDto>> create(@RequestBody List<TimetableDto> timetableDtos) {
        return ResponseEntity.ok(timetableManager.createTimetables(timetableDtos));
    }
    @PostMapping("/list/create")
    public ResponseEntity<List<TimetableDto>> createTimetable(@RequestBody List<TimetableDto> timetableDtos) {
        return ResponseEntity.ok(timetableManager.createTimetables(timetableDtos));
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<TimetableDto>> getTeacherTimetable(@RequestParam String teacherCode) {
        return ResponseEntity.ok(timetableManager.fetchTeacherTimetable(teacherCode));
    }

    @GetMapping("/class")
    public ResponseEntity<List<TimetableDto>> getClassTimetable(
            @RequestParam String gradeLevel,
            @RequestParam String section,
            @RequestParam String dayOfWeek) {
        return ResponseEntity.ok(timetableManager.fetchClassTimetable(gradeLevel, section, dayOfWeek));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimetableDto>> getAll() {
        return ResponseEntity.ok(timetableManager.fetchAllTimetables());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(timetableManager.removeTimetable(id));
    }
}