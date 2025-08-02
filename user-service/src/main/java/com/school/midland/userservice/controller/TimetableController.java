package com.school.midland.userservice.controller;


import com.school.midland.commonlib.dtos.TimetableDto;
import com.school.midland.userservice.service.timetable.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/midland/users/timetables")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping("/create")
    public ResponseEntity<TimetableDto> createTimetable(@RequestBody TimetableDto timetableDto) {
        TimetableDto createdTimetable =timetableService.createTimetable(timetableDto);
        return ResponseEntity.ok(createdTimetable);
    }
    // POST: Create multiple timetables
    @PostMapping("/list/create")
    public ResponseEntity<List<TimetableDto>> createTimetables(@RequestBody List<TimetableDto> timetableDtos) {
        List<TimetableDto> createdTimetables = timetableService.createTimetables(timetableDtos);
        return ResponseEntity.ok(createdTimetables);
    }

    // GET: Get timetable by teacher code
    @GetMapping("/teacher/{teacherCode}")
    public ResponseEntity<List<TimetableDto>> getTeacherTimetable(@PathVariable String teacherCode) {
        List<TimetableDto> timetableDto = timetableService.getTeacherTimetable(teacherCode);
        return ResponseEntity.ok(timetableDto);
    }


    @GetMapping("/class")
    public ResponseEntity<List<TimetableDto>> getTimetableForClass(
            @RequestParam String gradeLevel,
            @RequestParam String section,
            @RequestParam String dayOfWeek) {
        List<TimetableDto> timetableDtos = timetableService.getTimetableForClass(gradeLevel, section, dayOfWeek);
        return ResponseEntity.ok(timetableDtos);
    }


    @GetMapping("/all")
    public ResponseEntity<List<TimetableDto>> getAllTimetables() {
        List<TimetableDto> allTimetables = timetableService.getAllTimetables();
        return ResponseEntity.ok(allTimetables);
    }

    // DELETE: Timetable by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTimetableById(@PathVariable Long id) {
        String result = timetableService.deleteTimetableById(id);
        return ResponseEntity.ok(result);
    }
}